package com.example.weather;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weather.modelo.SignUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private EditText TxtUser, TxtPassword;
    private FirebaseAuth Auth;
    private Resources Res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxtUser = (EditText)findViewById(R.id.txtEmail2);
        TxtPassword = (EditText)findViewById(R.id.txtPass2);
        Res = this.getResources();

        Auth = FirebaseAuth.getInstance();
    }

    public void SignUpLink(View view){
        Intent intent = new Intent(Home.this, SignUp.class);
        startActivity(intent);
    }

    public void Login(View view){
        String email = TxtUser.getText().toString();
        String pass = TxtPassword.getText().toString();

        if (email.isEmpty() ||  pass.isEmpty()){
            Toast toast = Toast.makeText(Home.this, Res.getString(R.string.Error_Empty), Toast.LENGTH_LONG);
            toast.show();
            TxtUser.setError("*");
            TxtPassword.setError("*");
        }else{



            Auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Home.this,MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast toast = Toast.makeText(Home.this, Res.getString(R.string.Error), Toast.LENGTH_LONG);
                                toast.show();
                                TxtUser.setError("*");
                                TxtPassword.setError("*");
                            }

                        }
                    });


        }


    }

}
