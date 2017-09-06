package nathanelucas.findmyschool.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import nathanelucas.findmyschool.R;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    //variaveis do firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //inicializacao do firebase
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d("logado", "onAuthStateChanged:signed_in " + user.getUid());
                }
                else{
                    Log.d("Deslogado", "onAuthStateChanged: signed_out ");
                }
            }
        };

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        if(mAuth.getCurrentUser() != null){
            //Inicia a proxima atividade aqui, chama a tela de lista eu acho
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void createUserFirebase(View view){
        EditText emailField = (EditText) findViewById(R.id.usernameField);
        EditText passwordField = (EditText) findViewById(R.id.passField);
        EditText passwordField2 = (EditText) findViewById(R.id.confirmPassField);
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String password2 = passwordField2.getText().toString();

        if(Objects.equals(password, password2)) {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.d("C. user por firebase", "createUserWithEmail: onComplete: " + task.isSuccessful());

                    if (!task.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "Não foi possível criar o usuário", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignupActivity.this, "Usuario criado com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }
        else
            Toast.makeText(SignupActivity.this, "As senhas tem que ser iguais", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        createUserFirebase(view);
    }
}
