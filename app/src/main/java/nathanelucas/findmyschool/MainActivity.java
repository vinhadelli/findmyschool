package nathanelucas.findmyschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAnimation();
    }

    public void initAnimation() {
        Animation anim01 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        Animation anim02 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);


        findViewById(R.id.logoIcon).startAnimation(anim01);

        anim02.setStartOffset(1600);
        findViewById(R.id.logoName).startAnimation(anim02);
    }

    public void passToLogin(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }
}
