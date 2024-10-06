package enginneer.tahir.messagingapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.shashank.sony.fancytoastlib.FancyToast

class LogInActivty : AppCompatActivity() {
    private var auth = Firebase.auth
    private lateinit var buttonLogIn : Button
    private lateinit var buttonsiggup : Button
    private lateinit var edtEmail : EditText
    private lateinit var edtPass : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in_activty)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()
        auth=FirebaseAuth.getInstance()
        buttonsiggup = findViewById(R.id.signupBtn)
        buttonLogIn = findViewById(R.id.loginBtn)
        edtEmail = findViewById(R.id.edtEmail)
        edtPass = findViewById(R.id.edtPass)



        buttonsiggup.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        buttonLogIn.setOnClickListener {
            var email = edtEmail.text.toString()
            var password = edtPass.text.toString()
            logIn(email,password)
        }

    }

    @SuppressLint("SuspiciousIndentation")
    private fun logIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                val intent = Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                    FancyToast.makeText(this, "Log In Succesfull", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show()
                } else {
                    FancyToast.makeText(this, "Error !! Invalid User", FancyToast.LENGTH_SHORT,FancyToast.CONFUSING,false).show()

                }
            }


    }

    override fun onStart() {
        super.onStart()
        val currentUser : FirebaseUser? = auth.currentUser
        if (currentUser != null)
        {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}