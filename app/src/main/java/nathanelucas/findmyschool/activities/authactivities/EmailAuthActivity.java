package nathanelucas.findmyschool.activities.authactivities;

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

import nathanelucas.findmyschool.activities.BuscaActivity;
import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.activities.MetodoActivity;

public class EmailAuthActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_auth);

        mAuth = FirebaseAuth.getInstance();
    }

    private void createAccount() {

        TextView emailField =((TextView) findViewById(R.id.criarEmail));
        TextView passwordField =((TextView) findViewById(R.id.criarSenha));
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if(!(email.contains("@") && email.endsWith(".com"))){
            Toast.makeText(this, R.string.invalide_email, Toast.LENGTH_SHORT).show();
            emailField.setText("");
            passwordField.setText("");
            return;
        }

        if (password.length() < 6){
            Toast.makeText(this, R.string.invalide_password, Toast.LENGTH_SHORT).show();
            emailField.setText("");
            passwordField.setText("");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(EmailAuthActivity.this, MetodoActivity.class));
                        } else {
                            Toast.makeText(EmailAuthActivity.this, R.string.fail_creation, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {

        createAccount();
    }

    @Override
    protected void onStop() {
        super.onStop();

        finish();
    }
}
