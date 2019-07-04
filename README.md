# Easy-SharedPreferences
A Simple Library to store  data on shared preferences

Spec
[![Release](https://jitpack.io/v/amanpreetyatin/Repo.svg)]
(https://jitpack.io/#AmanpreetYatin/Easy-SharedPreferences/0.1.0
)


<b>EasySharedPreferences is a powerful library to work with key-value storages in Android </b>

<b>Download</b><br>
Step 1. Add this in your root (Project) build.gradle at the end of repositories:<br>
<code style="background-color:#f6f8fa;font-size:84%">
allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
    </code>
    <br>
    Step 2. Add the dependency<br>
<code style="background-color:#f6f8fa;font-size:84%">
	        implementation 'com.github.AmanpreetYatin:Easy-SharedPreferences:0.1.0' </code>

   
<b>Usage</b>
<br>
//initilize only ones in application class<br>
 <code>
 override fun onCreate() {
        super.onCreate()
        EasySharedPreferenceConfig.initDefault(EasySharedPreferenceConfig.Builder()
                .setFileName("easy_preference")
                .setMode(Context.MODE_PRIVATE)
                .build())
    }</code>
    <br>
    
EasySharedPreference.Companion.putString("KEY", "value");<br>
EasySharedPreference.Companion.getString("KEY", "DefaultValue");

