# Easy-SharedPreferences
A Simple Library to store  data on shared preferences


![Release](https://jitpack.io/v/AmanpreetYatin/Easy-SharedPreferences.svg)<br><br>
(https://jitpack.io/#AmanpreetYatin/Easy-SharedPreferences/0.1.1
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
	        implementation 'com.github.AmanpreetYatin:Easy-SharedPreferences:0.1.1' </code>

   
<b>Usage</b><br>

<b>JAVA</b><br>
	<code>
		EasySharedPreferenceConfig.Companion.initDefault(new EasySharedPreferenceConfig.Builder()
                .inputFileName("easy_preference")
                .inputMode(Context.MODE_PRIVATE)
		.build());</code><br>
	
<b>Kotlin</b><br>
 <code>
 override fun onCreate() {
        super.onCreate()<br>
        EasySharedPreferenceConfig.initDefault(EasySharedPreferenceConfig.Builder()
                .inputFileName("easy_preference")
                .inputMode(Context.MODE_PRIVATE)
                .build())
    }</code>
    <br>
    
   <b>EXAMPLE::</b><br>

EasySharedPreference.Companion.putString("KEY", "value");<br>
EasySharedPreference.Companion.getString("KEY", "DefaultValue");

