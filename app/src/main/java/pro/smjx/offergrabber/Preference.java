package pro.smjx.offergrabber;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static final String IS_LOGGED_IN = "is_logged_in";

    public Preference(Context context){
        sharedPreferences = context.getSharedPreferences("product",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoginState(boolean state){
        editor.putBoolean(IS_LOGGED_IN,state);
        editor.commit();
    }

    public boolean getLoginState(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }
}
