# StarryNight

**StarryNight** provides a piece of beautiful starry sky for you in a handy, customizable way as an Android library.

![image](/sample/sample1.gif)

## Get started

In project's `build.gradle`:

```groovy
allprojects {
    repositories {
        // other repositories...
        maven { url "https://jitpack.io"}
    }
}
```

In app's `build.gradle`:

```groovy
dependencies {
    // other dependencies...
    compile 'com.github.chihane:StarrySky:1.0.0'
}
```

Use `StarrySky` like a regular `ViewGroup` :

```xml
<chihane.starrysky.StarrySky
    android:id="@+id/starrySky"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

That's it!

## Customization

`StarMaker` is the ONE who dominates the `StarrySky` and creates all `Star`s, decides how stars are like, how many they are and how they move.

These are things he can do:

```java
StarMaker starMaker = StarMaker.with(context)
                .seed(seed)                             // The random seed
                .density(density)                       // How many stars there should be, [0f, 1f]
                .baseMagnitude(baseMagnitude)           // Base point of stars' luminance, [-6, 6]
                .magnitudeAmplitude(magnitudeAmplitude) // How random stars' luminance will be, [0, 6]
                .createGiantStar()                      // Red & blue giant stars will be created 
                .starTwinkles();                        // Stars will twinkle
```

Then let him dominate the sky:

```java
sky.dominateBy(starMaker);
```

### For more details, check the `app` demo. 

## Inspired by

[NightView](https://github.com/Boris-Em/NightView)

## About me

**Chihane Habana**

- <http://chihane.in>
- <chihane@yeah.net>
- <http://weibo.com/chihaneh>

## License

[The MIT License](http://chihane.in/license)