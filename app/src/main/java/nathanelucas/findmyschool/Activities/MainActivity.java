package nathanelucas.findmyschool.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;



import nathanelucas.findmyschool.R;

public class MainActivity extends AppCompatActivity {

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
        Animation anim01 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        Animation anim02 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);


        findViewById(R.id.logoIcon).startAnimation(anim01);

        anim02.setStartOffset(1600);
        anim02.setAnimationListener(new Animation.AnimationListener() {
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
        findViewById(R.id.logoName).startAnimation(anim02);
    }

    public void passToLogin(){
        finish();startActivity(new Intent(this, LoginActivity.class));
    }
}
