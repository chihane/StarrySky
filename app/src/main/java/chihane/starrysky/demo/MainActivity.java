package chihane.starrysky.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import chihane.starrysky.StarMaker;
import chihane.starrysky.StarrySky;

public class MainActivity extends AppCompatActivity {
    private StarrySky sky;

    private int seed = 1234;
    private float density = 0.30f;
    private int baseMagnitude = 0;
    private int magnitudeAmplitude = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        sky = (StarrySky) findViewById(R.id.starrySky);

        sky.setBackgroundColor(StarrySky.BACKGROUND_COLOR_MIDNIGHT);

        StarMaker starMaker = StarMaker.with(this)
                .seed(seed)
                .density(density)
                .baseMagnitude(baseMagnitude)
                .magnitudeAmplitude(magnitudeAmplitude)
                .createGiantStar()
                .starTwinkles();
        sky.dominateBy(starMaker);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StarMaker starMaker = StarMaker.with(MainActivity.this)
                        .seed(++seed)
                        .density(density)
                        .baseMagnitude(baseMagnitude)
                        .magnitudeAmplitude(magnitudeAmplitude)
                        .createGiantStar()
                        .starTwinkles();
                sky.dominateBy(starMaker);
                // sky.refresh();
            }
        });
    }
}
