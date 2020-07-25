package com.example.tutorfinderelearning.CommonFiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tutorfinderelearning.R;
import com.example.tutorfinderelearning.User.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DashBoardScreen extends AppCompatActivity {


    private Button signup,signin;
    private FirebaseAuth firebaseAuth;
    private ImageView up_logo;
    private TextInputEditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_screen);
        firebaseAuth = FirebaseAuth.getInstance();
        init_Views();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent it = new Intent(DashBoardScreen.this, RegisterActivity.class);
                        Pair[] pairs = new Pair[1];
                        pairs[0]= new Pair<View, String>(up_logo, "logo_up_image");
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DashBoardScreen.this, pairs);
                        startActivity(it, options.toBundle());

                    }
                },100);

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSignIn();
            }
        });
    }

    private void init_Views() {
        up_logo =(ImageView)findViewById(R.id.imageView);
        signup = (Button)findViewById(R.id.signup);
        signin = (Button)findViewById(R.id.signin);
        username = (TextInputEditText)findViewById(R.id.username);
        password = (TextInputEditText)findViewById(R.id.password);
    }

    private void UserSignIn() {
        String email_id, pass;
        email_id = username.getText().toString();
        pass = password.getText().toString();
        if(!TextUtils.isEmpty(email_id) && !TextUtils.isEmpty(pass) ) {
            firebaseAuth.signInWithEmailAndPassword(email_id,pass ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(DashBoardScreen.this, "Login Success", Toast.LENGTH_LONG).show();
                        Intent it = new Intent(getApplicationContext(), FinalDashBoard.class);
                        startActivity(it);
                    }
                    else{
                        Toast.makeText(DashBoardScreen.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else{
            Toast.makeText(DashBoardScreen.this, "Please Enter Your Email and Password", Toast.LENGTH_LONG).show();
        }


    }
}