package nathanelucas.findmyschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Button loginBtn = (Button) findViewById(R.id.loginBtn);
//        loginBtn.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View view)
//    {
//        passToMenu(view);
//    }

    public void passToMenu(View view){
        startActivity(new Intent(this, MenuTesteActivity.class));
    }
}
