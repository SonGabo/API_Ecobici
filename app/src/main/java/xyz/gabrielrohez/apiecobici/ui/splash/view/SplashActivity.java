package xyz.gabrielrohez.apiecobici.ui.splash.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.gabrielrohez.apiecobici.data.preferences.MySharedPreferences;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsActivity;
import xyz.gabrielrohez.apiecobici.ui.splash.presenter.SplashPresenter;
import xyz.gabrielrohez.apiecobici.ui.splash.presenter.SplashPresenterIn;
import xyz.gabrielrohez.apiecobici.R;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @BindView(R.id.splash_title)
    TextView title;

    private Animation animation;
    private SplashPresenterIn presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ButterKnife.bind(this);

        animation = AnimationUtils.loadAnimation(this, R.anim.animation_logo);
        title.startAnimation(animation);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        final String currentDateandTime = sdf.format(new Date());

        presenter = new SplashPresenter(this);
        MySharedPreferences.getInstance(this);

        presenter.obtainAccessToken(this);
    }

    @Override
    public void openNextActivity() {
        startActivity(new Intent(SplashActivity.this, MapsActivity.class));
        finish();
    }

    @Override
    public void showError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.error_in_request);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.obtainAccessToken(SplashActivity.this);
            }
        });
        builder.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
