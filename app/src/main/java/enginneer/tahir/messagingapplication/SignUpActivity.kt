package enginneer.tahir.messagingapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shashank.sony.fancytoastlib.FancyToast

class SignUpActivity : AppCompatActivity() {
    private var auth = Firebase.auth
    private lateinit var mDBRef : DatabaseReference
    private lateinit var buttonSignup : Button
    private lateinit var edtName : EditText
    private lateinit var edtEmail : EditText
    private lateinit var edtPass : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPass = findViewById(R.id.edtPass)
        buttonSignup =findViewById(R.id.signupBtn)

        buttonSignup.setOnClickListener {
          var  name = edtName.text.toString()
          var  email = edtEmail.text.toString()
          var  password = edtPass.text.toString()
            signUp(name,email,password)
        }
    }

    private fun signUp(name:String ,email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDataBase(name,email,auth.currentUser?.uid)
                    val intent = Intent(this@SignUpActivity,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                    FancyToast.makeText(this,"SignUp Success",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()

                } else {
                    FancyToast.makeText(this,"Sign Up Failed",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
                }
            }

    }

    private fun addUserToDataBase(name: String, email: String, uid: String?) {
        mDBRef = FirebaseDatabase.getInstance().reference
        if (uid != null) {
            mDBRef.child("User").child(uid).setValue(User(name,email,uid))
        }


    }
}