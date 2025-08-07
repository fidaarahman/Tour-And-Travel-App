package com.fmstudio.tourandtravelplanner.ui.common

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.databinding.ActivityLoginBinding
import com.fmstudio.tourandtravelplanner.ui.admin.activities.AdminDashBoardActivity
import com.fmstudio.tourandtravelplanner.ui.user.activities.MainActivity
import com.fmstudio.tourandtravelplanner.ui.user.activities.SignUpActivity
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private val GOOGLE_SIGN_IN_REQUEST_CODE = 1001
    private val ADMIN_EMAIL = "fidaurrahman@gmail.com"
    private val ADMIN_PASSWORD = "Fida2580"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupValidation()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.txtCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()

            if (!email.endsWith("@gmail.com")) {
                binding.etEmail.error = "Email must end with @gmail.com"
                return@setOnClickListener
            }
            if (password.length < 6) {
                binding.etPassword.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            // 👉 Check static admin login first
            if (email == ADMIN_EMAIL && password == ADMIN_PASSWORD) {
                Toast.makeText(this, "Welcome Admin!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AdminDashBoardActivity::class.java))
                finish()
                return@setOnClickListener
            }

            // Regular user Firebase login
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        val exception = task.exception
                        val errorMessage = if (exception is FirebaseAuthException) {
                            when (exception.errorCode) {
                                "ERROR_USER_NOT_FOUND", "ERROR_WRONG_PASSWORD" -> {
                                    "Please enter correct credentials."
                                }
                                else -> exception.message
                            }
                        } else {
                            exception?.message
                        }
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.txtForgotPassword.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val emailInput = dialogView.findViewById<android.widget.EditText>(R.id.et_reset_email)

            val alertDialog = androidx.appcompat.app.AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton("Send") { dialog, _ ->
                    val email = emailInput.text.toString().trim()

                    if (email.isEmpty()) {
                        Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    if (!email.endsWith("@gmail.com")) {
                        Toast.makeText(this, "Only Gmail addresses are supported", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    // Firebase sends reset email (if registered)
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("ForgotPassword", "Reset email sent successfully to: $email")
                                Toast.makeText(
                                    this,
                                    "If this email is registered, a reset link has been sent.",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                Log.e("ForgotPassword", "Failed to send reset email", task.exception)
                                Toast.makeText(
                                    this,
                                    "Failed to send reset email: ${task.exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }


                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .create()

            alertDialog.show()
        }


        binding.btnGoogle.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                auth.signInWithCredential(credential)
                    .addOnCompleteListener { authResult ->
                        if (authResult.isSuccessful) {
                            val user = auth.currentUser
                            val db = FirebaseFirestore.getInstance()

                            val emailKey = user?.email?.replace(".", "_") ?: "unknown_email"

                            val userData = hashMapOf(
                                "name" to user?.displayName,
                                "email" to user?.email,
                                "photoUrl" to user?.photoUrl.toString(),
                                "timestamp" to System.currentTimeMillis()
                            )

                            db.collection("users").document(emailKey).get()
                                .addOnSuccessListener { document ->
                                    if (!document.exists()) {
                                        db.collection("users").document(emailKey).set(userData)
                                    }
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "Firestore error", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(this, "Firebase Auth Failed", Toast.LENGTH_SHORT).show()
                        }
                    }

            } catch (e: ApiException) {
                Toast.makeText(this, "Google Sign-In Failed: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupValidation() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString().trim()
                binding.etEmail.error =
                    if (!email.endsWith("@gmail.com")) "Email must end with @gmail.com" else null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                binding.etPassword.error =
                    if (password.length < 6) "Password must be at least 6 characters" else null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
