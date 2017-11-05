package nathanelucas.findmyschool.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import nathanelucas.findmyschool.activities.authactivities.*;
import nathanelucas.findmyschool.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            startActivity(new Intent(this, MetodoActivity.class));
        }

    }

    private void signIn() {

        TextView emailField = ((TextView) findViewById(R.id.emailField));
        TextView passwordField = ((TextView) findViewById(R.id.passwordField));
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if(!(email.contains("@") && email.endsWith(".com"))){
            Toast.makeText(this, "@string/invalid_email", Toast.LENGTH_SHORT).show();
            emailField.setText("");
            passwordField.setText("");
            return;
        }

        if (password.length() < 6){
            Toast.makeText(this, "@string/invalid_password", Toast.LENGTH_SHORT).show();
            emailField.setText("");
            passwordField.setText("");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, MetodoActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "@string/user_not_found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id){
            case R.id.emailBtn:
                signIn();
                break;
            case R.id.createAccount:
                startActivity(new Intent(this, EmailAuthActivity.class));
                break;
            case R.id.gBtn:
                startActivity(new Intent(this, GoogleAuthActivity.class));
                break;
            case R.id.fbBtn:
                startActivity(new Intent(this, FacebookAuthActivity.class));
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mAuth.getCurrentUser() != null){
            finish();
        }
    }
}
