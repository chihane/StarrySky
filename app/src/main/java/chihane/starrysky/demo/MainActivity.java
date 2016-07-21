package chihane.starrysky.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import chihane.starrysky.StarMaker;
import chihane.starrysky.StarrySky;

public class MainActivity extends AppCompatActivity {
    private StarrySky sky;

    private int seed = 1234;
    private float density = 0.05f;
    private int baseMagnitude = 0;
    private int magnitudeAmplitude = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sky = (StarrySky) findViewById(R.id.starrySky);

        StarMaker starMaker = StarMaker.with(this)
                .seed(seed)
                .density(density)
                .baseMagnitude(baseMagnitude)
                .magnitudeAmplitude(magnitudeAmplitude);
        sky.dominateBy(starMaker);
    }

    @Override
    public void onBackPressed() {
        StarMaker starMaker = StarMaker.with(this)
                .seed(++seed)
                .density(density)
                .baseMagnitude(baseMagnitude)
                .magnitudeAmplitude(magnitudeAmplitude);
        sky.dominateBy(starMaker);
//        sky.refresh();
    }
}
