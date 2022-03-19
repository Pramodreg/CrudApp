 package com.example.firebasecrudapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

 public class RegistrationActivity extends AppCompatActivity {

//     creating variables for registration
    private TextInputEditText userNameEdt, pwdEdt, cnfPwdEdt;
    private Button registerBtn;
    private ProgressBar loadingPB;
    private TextView loginTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Initializing all of the variables
        setContentView(R.layout.activity_registration);
        userNameEdt= findViewById(R.id.idEdtUserName);
        pwdEdt= findViewById(R.id.idEdtPwd);
        cnfPwdEdt= findViewById(R.id.idedtCnfPwd);
        registerBtn= findViewById(R.id.idBtnRegister);
        loadingPB= findViewById(R.id.idPBLoading);
        loginTV= findViewById(R.id.idTVLogin);
//        getting instance from firebaseauth
        mAuth= FirebaseAuth.getInstance();
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadingPB.setVisibility(View.VISIBLE);
               String userName = userNameEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                String cnfPwd = cnfPwdEdt.getText().toString();
                if (!pwd.equals(cnfPwd)){
                    Toast.makeText(RegistrationActivity.this, "Please Re-check the password", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(cnfPwd)){
                    Toast.makeText(RegistrationActivity.this, "Please add your details", Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.createUserWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, "user registered..", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            }else {
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, "Unable to register user", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }
}