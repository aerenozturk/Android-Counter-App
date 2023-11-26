package com.example.sayac1;
import android.content.Context;
import android.content.SharedPreferences;
public class ConfigClass {
    int upperLimit;
    int lowerLimit;
    int currentValue;


    boolean upperVib;
    boolean lowerVib;
    boolean upperSound;
    boolean lowerSound;

    final static String UPPER_LIMIT = "upperLimit";
    final static String LOWER_LIMIT = "lowerLimit";
    final static String CURRENT_VALUE = "currentValue";
    final static String UPPER_VIB = "upperVib";
    final static String LOWER_VIB = "lowerVib";
    final static String UPPER_SOUND= "upperSound";
    final static String LOWER_SOUND = "lowerSound";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static ConfigClass configClass=null;

    final static String PREF_NAME="setup";

    private ConfigClass(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static ConfigClass getInstance(Context context) {
        if(configClass==null){
            configClass=new ConfigClass(context);
        }
        return configClass;
    }
    public void loadData(){
        upperLimit=sharedPreferences.getInt(UPPER_LIMIT,10);
        lowerLimit=sharedPreferences.getInt(LOWER_LIMIT,0);
        currentValue=sharedPreferences.getInt(CURRENT_VALUE,lowerLimit);
        upperVib=sharedPreferences.getBoolean(UPPER_VIB,true);
        lowerVib=sharedPreferences.getBoolean(LOWER_VIB,true);
        upperSound=sharedPreferences.getBoolean(UPPER_SOUND,true);
        lowerSound=sharedPreferences.getBoolean(LOWER_SOUND,true);
    }
    public void saveData(){
       editor.putInt(UPPER_LIMIT, upperLimit);
       editor.putInt(LOWER_LIMIT, lowerLimit);
       editor.putInt(CURRENT_VALUE, currentValue);
       editor.putBoolean(UPPER_VIB,upperVib);
       editor.putBoolean(LOWER_VIB,lowerVib);
       editor.putBoolean(UPPER_SOUND,upperSound);
       editor.putBoolean(LOWER_SOUND,lowerSound);
       editor.commit();
    }
    public void setData(int upperLimit, int lowerLimit, int currentValue,
                        boolean upperVib, boolean lowerVib, boolean upperSound, boolean lowerSound){
        this.upperLimit=upperLimit;
        this.lowerLimit=lowerLimit;
        this.currentValue=currentValue;
        this.upperVib=upperVib;
        this.lowerVib=lowerVib;
        this.upperSound=upperSound;
        this.lowerSound=lowerSound;

    }

}
