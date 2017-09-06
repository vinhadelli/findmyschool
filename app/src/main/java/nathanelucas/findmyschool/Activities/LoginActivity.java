package nathanelucas.findmyschool;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static android.R.attr.onClick;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    //variaveis do firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //variaveis do login google
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;

    //Views
    private final int signEmail = R.id.sign_email;
    private final int signGoogle = R.id.sign_google;
    private final int createUserEmail = R.id.create_user_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //definindo clickListeners
        findViewById(signEmail).setOnClickListener(this);
        SignInButton gBtn = (SignInButton) findViewById(signGoogle);
        gBtn.setOnClickListener(this);
        gBtn.setTextAlignment(SignInButton.TEXT_ALIGNMENT_CENTER);
        findViewById(createUserEmail).setOnClickListener(this);

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

        // Configuracao para sign pelo google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

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

    //metodos do login com email e senha
    public void createUserFirebase(){
        EditText emailField = (EditText) findViewById(R.id.emailField);
        EditText passwordField = (EditText) findViewById(R.id.passwordField);
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                Log.d("C. user por firebase", "createUserWithEmail: onComplete: " + task.isSuccessful());

                if(!task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Não foi possível criar o usuário", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signInFirebase(){
        EditText emailField = (EditText) findViewById(R.id.emailField);
        EditText passwordField = (EditText) findViewById(R.id.passwordField);
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task){
                Log.d("Login por firebase", "onComplete: " + task.isSuccessful());

                if(!task.isSuccessful()){
                    Log.w("Erro login", "onComplete: ", task.getException());
                    Toast.makeText(LoginActivity.this, "Não foi possível executar loggin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //metodo resultado do chamado da verificacao do google
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                //colocar tratamento pra error no login
                Toast.makeText(this, "Não foi possível executar login", Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        }
    }

    //metodo que faz o sign in do google
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("Login pelo Google", "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
//        showProgressDialog(); // Aqui vai aquela telinha com a bolinha girando pra mostrar carregamento
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Login G Sucedido", "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser(); caso precise levar as informações do usuario
//                            updateUI(user); Chamar a proxima tela aqui
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Login g fracasso", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Não foi possivel executar login",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null); Uma tela de falha de login aqui depois
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog(); Aqui é onde se encerra aquela tela com a bolinha de carregamento girando
                        // [END_EXCLUDE]
                    }
                });
    }

    // Botao de sign Google
    public void signInGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //Exigencia da API do google
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Erro de conexao", "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id){
            case signEmail:
                signInFirebase();
                break;
            case signGoogle:
                signInGoogle();
                break;
            case createUserEmail:
                createUserFirebase();
                break;
        }
    }
}
