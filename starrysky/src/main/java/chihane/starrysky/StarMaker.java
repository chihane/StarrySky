package chihane.starrysky;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StarMaker {
    private Context context;

    private static final float DENSITY_MULTIPLIER = 0.0002f;
    private static final int SIZE_MIN = 2;
    private static final int SIZE_MAX = 8;

    private float density = 0.05f;
    private int magnitude = Star.MAGNITUDE_MAX;
    private int magnitudeAmplitude = 0;

    private long seed;

    public StarMaker(Context context) {
        this.context = context;
    }

    public static StarMaker with(Context context) {
        return new StarMaker(context);
    }

    public StarMaker density(@FloatRange(from = 0, to = 1f) float density) {
        this.density = density;
        return this;
    }

    public StarMaker baseMagnitude(@IntRange(from = Star.MAGNITUDE_MAX, to = Star.MAGNITUDE_MIN)
                                   int magnitude) {
        this.magnitude = magnitude;
        return this;
    }

    public StarMaker magnitudeAmplitude (@IntRange(from = 0, to = Star.MAGNITUDE_MIN)
                                    int magnitudeAmplitude) {
        this.magnitudeAmplitude = magnitudeAmplitude;
        return this;
    }

    public StarMaker seed(long seed) {
        this.seed = seed;
        return this;
    }

    List<Star> 星たちよーこの造星者の名のもとにー現れーーー(int width, int height) {
        List<Star> galaxy = new ArrayList<>();

        final int quantity = (int) (width * height * density * DENSITY_MULTIPLIER);
        Log.d("chihane", "Stars generated: " + quantity);

        Random random = new Random(seed = (seed == 0 ? System.currentTimeMillis() : seed));
        for (int i = 0; i < quantity; i++) {
            Star star = new Star(context);
            star.longitude = (float) (random.nextDouble() * width);
            star.latitude = (float) (random.nextDouble() * height);
            star.size = SIZE_MIN + (float) (random.nextDouble() * (SIZE_MAX - SIZE_MIN));

            // actualMagnitude ∈ [Star.MAGNITUDE_MAX, Star.MAGNITUDE_MIN)
            double actualMagnitude = magnitude + (random.nextDouble() * 2 - 1) * magnitudeAmplitude;
            if (actualMagnitude > Star.MAGNITUDE_MIN) actualMagnitude = Star.MAGNITUDE_MIN;
            if (actualMagnitude < Star.MAGNITUDE_MAX) actualMagnitude = Star.MAGNITUDE_MAX;

            star.magnitude = (int) actualMagnitude;
//            Log.d("chihane", "Star glows in: " + star.magnitude);

            galaxy.add(star);
        }

        return galaxy;
    }
}
