package uni.UNI910BAD2.elitetyc_plugin.message.demo.tester;

import android.content.Context;
import android.util.Log;

import com.umeng.message.PushAgent;
import com.umeng.message.api.UPushAliasCallback;

public class UPushAlias {

    private static final String TAG = "UPushAlias";

    /**
     * 增加别名
     */
    public static void add(Context context, String alias, String type) {
        PushAgent.getInstance(context).addAlias(alias, type, new UPushAliasCallback() {
            @Override
            public void onMessage(boolean success, String message) {
                Log.i(TAG, "add success:" + success + " msg:" + message);
            }
        });
    }

    /**
     * 删除别名
     */
    public static void delete(Context context, String alias, String type) {
        PushAgent.getInstance(context).deleteAlias(alias, type, new UPushAliasCallback() {
            @Override
            public void onMessage(boolean success, String message) {
                Log.i(TAG, "delete success:" + success + " msg:" + message);
            }
        });
    }

    /**
     * 绑定别名
     */
    public static void set(Context context, String alias, String type) {
        PushAgent.getInstance(context).setAlias(alias, type, new UPushAliasCallback() {
            @Override
            public void onMessage(boolean success, String message) {
                Log.i(TAG, "set success:" + success + " msg:" + message);
            }
        });
    }

    /**
     * 别名和Tag功能测试入口
     */
    public static void test(Context context) {
        set(context, "a", "123456");
    }
}
