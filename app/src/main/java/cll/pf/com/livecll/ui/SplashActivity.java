package cll.pf.com.livecll.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.router.CllRouter;
import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.router.RouterPath;

@CllRouter(value = ConstantPath.SPLASH_INDEX)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        startActivity(new Intent(this, IntroduceActivity.class));
        RouterPath.getInstance().navigation(this, ConstantPath.INTRODUCE_INDEX);
        finish();
    }
}
