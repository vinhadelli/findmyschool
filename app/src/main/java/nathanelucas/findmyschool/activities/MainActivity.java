package nathanelucas.findmyschool.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;



import nathanelucas.findmyschool.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initAnimation();
    }

    public void initAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                passToLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        findViewById(R.id.logoIcon).startAnimation(anim);
    }

    public void passToLogin(){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
