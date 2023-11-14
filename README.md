<p align="left">
<a href="http://www.recivio.com">
  <img src="hhttps://app.recivio.com/images/logo.png"></a>
</p>
<h1>Android SDK from Recivio</h1>
<h2> The Best-in-Class Recycling&Loyalty widget your mobile apps. Integrate painlessly inside any Android App with <a href="http://www.recivio.com/go/live-chat?utm_source=github&utm_medium=readme&utm_campaign=androidsdk">recivio SDK </a>  </h2>

  [![Android API14+](https://img.shields.io/badge/Android-API_14+-green.svg)]()
  [![Java 6+](https://img.shields.io/badge/Java-6+-red.svg)]()
  [![License Apache 2.0](https://img.shields.io/badge/license-Apache%20License%202.0-red.svg)]()

<p align="center">
  <img src="https://cdn.worldvectorlogo.com/logos/kotlin-1.svg" width=25 alt="Icon"/> <b>Kotlin oriented</b> (Java fully supported)
</p>
  
**recivio** is the most complete <strong>Recycling&Loyalty</strong> solution for your mobile apps. H
Easy to integrate , once integrated you can track user data and gather user feedback. 

Run Surveys directly into your mobile apps and get the responses in one place. 


## Features

- [x] Register recycle coupons
- [x] Register your users
- [x] Sending marketing campaigns
- [x] Track events
- [x] Surveys
- [x] Polish localizations


## Requirements

- Android 4.0.1+ (API level 14+)
- Android Studio 2.0+
- Java 6+
(recivio SDK is now optimized for Kotlin, and it works also with pure Java projects)

## Setup recivio SDK

### Integration via Gradle or Maven dependency (Recommended)

To integrate the recivio SDK we recommend to use Gradle or Maven dependency

Instead of VERSION_NAME use the latest version name.

##### Since v3.1.0 the sdk is now hosted on [![](https://jitpack.io/v/com.recivio/Android-SDK.svg)](https://jitpack.io/#com.recivio/Android-SDK)  

Add it in your root `build.gradle` at the end of repositories:  
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency to your app `build.gradle`
```gradle
dependencies {
    implementation 'com.recivio:Android-SDK:<VERSION_NAME>'
}
```

You can find also `maven`, `sbt` and `leiningen` instruction in the [sdk JitPack page](https://jitpack.io/#com.recivio/Android-SDK)

##### Gradle (Deprecated, only up to v3.0.10)
In your module `build.gradle` add:

```gradle
buildscript {
    repositories {
        jcenter()
    }
}

dependencies {
    compile 'com.recivio:recivio-android-sdk:VERSION_NAME'
}
```

##### Maven (Deprecated, only up to v3.0.10)
```xml
<dependency>
  <groupId>com.recivio</groupId>
  <artifactId>recivio-android-sdk</artifactId>
  <version>VERSION_NAME</version>
  <type>pom</type>
</dependency>
```

### Manual integration (Discouraged)

We suggest to avoid manually integration of the SDK in your project, by the way it is still possible:

1. Download the `/recivio-android-sdk` folder and add it as module in your project.
2. Congratulations!

We recommend to use only the public methods of the class recivio.

### Configuration (Mandatory)

**1)** Create a new AndroidStudio project or open an existing one

**2)** If you already have defined a custom Application class in your project you can skip to step 3).
Create a class that extends the default android Application class

`Kotlin`
```kotlin
class CustomApplication: Application {
    override fun onCreate() {
        super.onCreate()
        //See step 3)
    }
}

```
`Java`
```java
public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //See step 3)
    }
}
```
Don't forget to declare this class in your `AndroidManifest.xml` file:
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="YOUR.PACKAGE">
    <application
        android:name=".CustomApplication" >
        //...
    </application>
</manifest>
```

**3)** Now you need to configure the recivio SDK in your custom Application class onCreate method:

`Kotlin`
```kotlin
class CustomApplication: Application {
    override fun onCreate() {
        super.onCreate()
        recivio.configure(application = this, recivioAppId = "YOUR_recivio_APP_ID")
    }
}
```
`Java`
```java
public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        recivio.configure(this, "YOUR_recivio_APP_ID");
    }
}
```
*OPTIONALLY*, if you want to specify a custom widget_color for the application ignoring the server-defined one you have to specify it in the configure method:

`Kotlin`
```kotlin
recivio.configure(application = this, recivioAppId = "YOUR_recivio_APP_ID", widgetColorInt = Color.RED)
```
`Java`
```java
recivio.configure(this, "YOUR_recivio_APP_ID", Color.RED);
```
*OPTIONALLY*, after the configure, if you want to enable the logging in console you have to call the following method.
Our suggest is to call it soon after the configure:

`Kotlin`
```kotlin
recivio.setVerboseLogging(enabled = BuildConfig.DEBUG)
//Passing BuildConfig.DEBUG, logging will be automatically disabled for the release apk
```
`Java`
```java
recivio.setVerboseLogging(BuildConfig.DEBUG);
//Passing BuildConfig.DEBUG, logging will be automatically disabled for the release apk
```

### Dashboard (Mandatory)

Don't forget to show a button or something to your user for opening the Support Activity. Just call the method `openSupport`:

`Kotlin`
```kotlin
recivio.openSupport(activity = this@Activity)
```
`Java`
```java
recivio.openSupport(Activity.this);
```


### User registration and logout (Optional but recommended)

If you don't have a login method inside your apps don't worry, users can use the chat inserting their emails.

By the way, if users login in your application please register them into recivio calling the method `registerUser:`.
Example:

`Kotlin`
```kotlin
recivio.registerUser(email = "abc@user.xyz")
```
`Java`
```java
recivio.registerUser("abc@user.xyz");
```

As seen above, you only need an email address to register the user
*BUT, OPTIONALLY,* you can set one or more other stuff to the registerUser task:

`Kotlin`
```kotlin
recivio.registerUser(
    email = "abc@user.xyz",
    userId = "pass007#",           //OPTIONALLY you can pass the user ID
    name = "John",            //OPTIONALLY you can pass the user name
    attributes = attributesMap, //OPTIONALLY you can pass some custom attributes (See the *Attributes* section below for the map building)
    company = companyMap,       //OPTIONALLY you can pass the user company informations (See the *Companies* section below for the map building)
    success = {                 //OPTIONALLY you can pass a callback to be notified of the success of the task
        //Called if the task completes successfully
    },
    failure = {                 //OPTIONALLY you can pass a callback to be notified of the failure of the task
        //Called if the task fails
    })
```
`Java`
```java
recivio.registerUser(
    "abc@user.xyz",
    "pass007#",                //OPTIONALLY you can pass the user ID or null
    "John",               //OPTIONALLY you can pass the user name or null
    attributesMap,          //OPTIONALLY you can pass some custom attributes or null (See the *Attributes* section below for the map building)
    companyMap,             //OPTIONALLY you can pass the user company informations or null (See the *Companies* section below for the map building)
    new Callback() {        //OPTIONALLY you can pass a callback to be notified of the success of the task or null
        @Override
        public Unit invoke() {
            //Called if the task completes successfully
            return null;
        }
    },
    new Callback() {        //OPTIONALLY you can pass a callback to be notified of the failure of the task or null
        @Override
        public Unit invoke() {
            //Called if the task fails
            return null;
        }
    });
```

So *user_id*, *name*, *attributes*, *company*, *success* and *failure* are totally optional.

Please remember to logout users from recivio when they logout in your application:

`Kotlin`
```kotlin
recivio.logoutUser()
```
`Java`
```java
recivio.logoutUser();
```
*OPTIONALLY,* you can pass a callback that will be executed when the logout task is completed:
`Kotlin`
```kotlin
recivio.logoutUser {
    // Do something
}
```
`Java`
```java
recivio.logoutUser(
    new Callback() {        //OPTIONALLY you can pass a callback to be notified of the success of the task or null
        @Override
        public Unit invoke() {
            // Do something
            return null;
        }
    }
);
```

Please mind that the logoutUser task is not runned synchronously, so if you try to do something like this in your code:

```kotlin
recivio.logoutUser()
recivio.registerUser(email)
```

the two task will conflict and the result won't be garanteed
Try something like this instead:

```kotlin
recivio.logoutUser {
    recivio.registerUser(email)
}
```

### Events (Optional)

Send to recivio every event you want to segment users better
Eg. This send an event that track a potential purchase

`Kotlin`
```kotlin
recivio.trackEvent(eventName = "added_to_cart")
```
`Java`
```java
recivio.trackEvent("added_to_cart");
```


## Permissions

The following permission will be AUTOMATICALLY added to the merged AndroidManifest of your application:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="YOUR.PACKAGE">
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    //...
</manifest>
```
`ACCESS_NETWORK_STATE` is used to verify if an internet connection is available
`INTERNET` is used to perform https requests
`READ_EXTERNAL_STORAGE` is used to upload file attachments
`WRITE_EXTERNAL_STORAGE` is used to save file attachments

## Proguard

The library needs the following rules to work with proguard enabled
```
-dontwarn java.lang.invoke.*
-dontwarn okio.**
-dontwarn com.recivio.**
-keepclasseswithmembers class android.animation.ValueAnimator {
    public static void setDurationScale(float);
}
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}
```

By the way should not be necessary to explicitly add them in your proguard file because they should be automatically inherited.

## Contributing

- If you **need help** or you'd like to **ask a general question**, open an issue or contact our support on [recivio.com](https://www.recivio.com)
- If you **found a bug**, open an issue.
- If you **have a feature request**, open an issue.
- If you **want to contribute**, submit a pull request.


## Acknowledgements

Made with ❤️ by [Igor Sawczuk](https://www.linkedin.com/in/isawczuk/) for Recivio.


## License

recivio Android SDK is available under the Apache License 2.0.
See the [recivio_LICENSE.txt](https://github.com/recivio/Android-SDK/blob/master/recivio_LICENSE.txt) file for more info.

