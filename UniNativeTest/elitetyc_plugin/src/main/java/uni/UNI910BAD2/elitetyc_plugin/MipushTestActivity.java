package uni.UNI910BAD2.elitetyc_plugin;

import android.content.Intent;
import android.os.Bundle;

import com.umeng.message.UmengNotifyClickActivity;
import com.umeng.message.entity.UMessage;

import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MipushTestActivity extends UmengNotifyClickActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
    }
/*    @Override
    public void onMessage(Intent intent) {
        super.onMessage(intent);  //此方法必须调用，否则无法统计打开数
        String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        UMessage msg = null;
        try {
            msg = new UMessage(new JSONObject(message));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Map<String, String> extra = msg.extra;
        if(extra != null){
            if(extra.get("type").equals("1")){     // 其中的 “1”为你在后台设置具体跳转到那个Activity的代表标志，需要跳转到的每个 Activity 取一个不同的标志
                Intent intent1 = new Intent(this,Main3Activity.class);   // 我想跳转到 Main3Activity
                intent1.putExtra("type",extra.get("type"));
                intent1.putExtra("username",extra.get("username"));
                intent1.putExtra("password",extra.get("password"));
                intent1.putExtra("sex",extra.get("sex"));
                startActivity(intent1);
            }
        }
    }*/
}
