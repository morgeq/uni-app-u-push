package uni.UNI910BAD2.elitetyc_plugin;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.umeng.commonsdk.UMConfigure;

import uni.UNI910BAD2.elitetyc_plugin.helper.PushHelper;

//import uni.UNI910BAD2.helper.PushHelper;

/**
 * 应用程序类
 */
public class MyApplication extends Application {

    private static final String CHANNEL_ID_STRING = "123";
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = null;
            channel = new NotificationChannel(CHANNEL_ID_STRING, getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID_STRING).build();
            startForeground(1, notification);
        }

        //日志开关
        UMConfigure.setLogEnabled(true);
        //预初始化
        PushHelper.preInit(this);
        //初始化
        initPushSDK();
    }

    private void startForeground(int i, Notification notification) {
        // 启动服务的地方
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context, YouMengPushIntentService.class));
        } else {
            context.startService(new Intent(context, YouMengPushIntentService.class));
        }
    }

    /**
     * 初始化推送SDK
     */
    private void initPushSDK() {
        /*
         * 若用户已同意隐私政策，直接初始化；
         * 若用户未同意隐私政策，待用户同意后，再通过PushHelper.init(...)方法初始化。
         */
        boolean agreed = MyPreferences.getInstance(this).hasAgreePrivacyAgreement();
        if (agreed) {
            //建议在线程中执行初始化
            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void run() {
                    PushHelper.init(getApplicationContext());
                }
            }).start();
        }
    }



}
