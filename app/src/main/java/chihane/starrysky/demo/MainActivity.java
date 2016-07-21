package chihane.starrysky.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import chihane.starrysky.StarMaker;
import chihane.starrysky.StarrySky;

public class MainActivity extends AppCompatActivity {
    private StarrySky sky;

    private int seed = 1234;
    private float density = 0.10f;
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
                .magnitudeAmplitude(magnitudeAmplitude)
                .createGiantStar();
        sky.dominateBy(starMaker);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StarMaker starMaker = StarMaker.with(MainActivity.this)
                        .seed(++seed)
                        .density(density)
                        .baseMagnitude(baseMagnitude)
                        .magnitudeAmplitude(magnitudeAmplitude)
                        .createGiantStar();
                sky.dominateBy(starMaker);
                // sky.refresh();
            }
        });
    }
}
