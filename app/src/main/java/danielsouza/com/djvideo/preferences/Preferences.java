package danielsouza.com.djvideo.preferences;

import android.content.Context;

import danielsouza.com.djvideo.constants.Constants;

public class Preferences {

    public void saveToPreferences(Context context, String preferenceName, String preferenceValue){
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public String readToPreferences(Context context, String preferenceName, String defaultValue) {
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }
}