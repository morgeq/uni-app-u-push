package uni.UNI910BAD2.elitetyc_plugin;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class Main3Activity {
    private JSONObject bun;
    //Bundle bun = getIntent().getExtras();

    private void getIntent() throws JSONException {
        if (bun != null) {
            String str_username = bun.getString("username");
            String str_password = bun.getString("password");
            String str_sex = bun.getString("sex");
        }
    }
}
