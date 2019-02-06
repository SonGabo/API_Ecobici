package xyz.gabrielrohez.apiecobici.ui.splash.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.gabrielrohez.apiecobici.R;
import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;
import xyz.gabrielrohez.apiecobici.data.preferences.MySharedPreferences;
import xyz.gabrielrohez.apiecobici.ui.main.MainActivity;
import xyz.gabrielrohez.apiecobici.ui.splash.presenter.SplashPresenter;
import xyz.gabrielrohez.apiecobici.ui.splash.presenter.SplashPresenterIn;

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

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_logo);
        title.startAnimation(animation);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        final String currentDateandTime = sdf.format(new Date());

        presenter = new SplashPresenter(this);
        MySharedPreferences.getInstance(this);

        if (MySharedPreferences.getInstance().getAccessToken().isEmpty()){
            presenter.obtainAccessToken(this);
        }else
            presenter.obtainInfoStations(this);
    }

    @Override
    public void dataReceived(List<AvailabilityStationsResponse.StationsStatus> stationsStatus, List<InfoStationResponse.Stations> stationsInfo) {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
