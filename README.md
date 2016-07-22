# StarryNight

**StarryNight** provides a piece of beautiful starry sky for you in a handy, customizable way as an Android library.

![image](/sample/sample1.gif)

> May there will be enough star light in the sky to make a beautiful starry night.

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

## Inspired by

[NightView](https://github.com/Boris-Em/NightView)

## About me

**Chihane Habana**

- <http://chihane.in>
- <chihane@yeah.net>
- <http://weibo.com/chihaneh>

## License

[The MIT License](http://chihane.in/license)