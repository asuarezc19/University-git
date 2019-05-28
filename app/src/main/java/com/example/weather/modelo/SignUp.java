package com.example.weather.modelo;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weather.Home;
import com.example.weather.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private EditText TxtUser, TxtPassword;
    private FirebaseAuth Auth;
    private Resources Res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        TxtUser = (EditText)findViewById(R.id.txtEmail2);
        TxtPassword = (EditText)findViewById(R.id.txtPass2);

        Res = this.getResources();
        Auth = FirebaseAuth.getInstance();
    }

    public void Register(View view) {

        String email = TxtUser.getText().toString();
        String password = TxtPassword.getText().toString();


        if (email.isEmpty() || password.isEmpty()) {

            Toast toast = Toast.makeText(SignUp.this, Res.getString(R.string.Error_Empty), Toast.LENGTH_LONG);
            toast.show();
            TxtUser.setError("*");
            TxtPassword.setError("*");


        } else {


            Auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast toast = Toast.makeText(SignUp.this, Res.getString(R.string.Signup), Toast.LENGTH_LONG);
                                toast.show();
                                Intent intent = new Intent(SignUp.this, Home.class);
                                startActivity(intent);
                            }
                        }
                    });

        }

    }
}
