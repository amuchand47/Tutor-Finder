package com.example.tutorfinderelearning.User;

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

import com.example.tutorfinderelearning.CommonFiles.DashBoardScreen;
import com.example.tutorfinderelearning.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText name , mobile_num, email_id, pass;
    private Button signup,signin;
    String user_id;
    private ImageView up_logo;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init_Views();

        firebaseAuth = FirebaseAuth.getInstance();

        user_id = firebaseAuth.getCurrentUser().getUid();

        firebaseFirestore = FirebaseFirestore.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              upload_data();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgainSignIn();
            }
        });
    }

    private void AgainSignIn() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(RegisterActivity.this, DashBoardScreen.class);
                Pair[] pairs = new Pair[1];
                pairs[0]= new Pair<View, String>(up_logo, "logo_up_image");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this, pairs);
                startActivity(it, options.toBundle());

            }
        },100);
    }

    private void upload_data() {
        final String user_name, user_mobile_num ,user_email, user_pass;
        user_name = name.getText().toString();
        user_mobile_num = mobile_num.getText().toString();
        user_email = email_id.getText().toString();
        user_pass = pass.getText().toString();

        DocumentReference documentReference = firebaseFirestore.collection("Users").document(user_id);
        if(!TextUtils.isEmpty(user_name) && !TextUtils.isEmpty(user_mobile_num) && !TextUtils.isEmpty(user_email) && !TextUtils.isEmpty(user_pass) ){
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("username", user_name);
            hashMap.put("usermobile",user_mobile_num);
            hashMap.put("useremail",user_email);
            hashMap.put("userpass",user_pass);

            documentReference.set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Data Updated", Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Data Not  Updated", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(RegisterActivity.this,"Fill data" , Toast.LENGTH_LONG).show();
        }

    }

    private void init_Views() {
        name = (TextInputEditText)findViewById(R.id.name);
        mobile_num = (TextInputEditText)findViewById(R.id.mobile_num);
        email_id = (TextInputEditText)findViewById(R.id.email_id);
        pass = (TextInputEditText)findViewById(R.id.pass);
        signup = (Button)findViewById(R.id.signup);
        signin = (Button)findViewById(R.id.signin);
        up_logo = (ImageView)findViewById(R.id.imageView);
    }
}