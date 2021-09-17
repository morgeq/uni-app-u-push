package uni.UNI910BAD2.elitetyc_plugin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.umeng.message.PushAgent;

import uni.UNI910BAD2.elitetyc_plugin.helper.PushHelper;

/*import uni.UNI910BAD2.helper.PushHelper;
import uni.UNI910BAD2.tester.UPushAlias;
import uni.UNI910BAD2.tester.UPushNotification;*/

/**
 * 应用入口Activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 推送消息测试
     */
    public void testPushMsg(View view) {
        //UPushNotification.send("来消息了", "标题", "这是内容，这是内容...");
    }

    /**
     * 别名、标签等设置测试
     */
    public void testMoreFunc(View view) {
        //UPushAlias.test(this);
    }

}
