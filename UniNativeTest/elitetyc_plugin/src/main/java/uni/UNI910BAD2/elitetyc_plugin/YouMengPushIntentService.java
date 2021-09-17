package uni.UNI910BAD2.elitetyc_plugin;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.test.internal.util.LogUtil;

import com.umeng.message.UmengMessageService;
import com.umeng.message.entity.UMessage;

import org.json.JSONObject;

public class YouMengPushIntentService extends UmengMessageService {
    private final  String TAG = "UPushModule===>>";
    private Context mContext;

    public Context getContext() {
        return this.mContext;
    }


    public void getNotification(Context context, String title, String msg) {
        //LogUtil.e("YouMengPushIntentService", "getNotification");
        Log.e(TAG, "getNotification");
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int id = (int) (System.currentTimeMillis() / 1000);
//        Intent intentClick = new Intent(this, NotificationClickReceiver.class);
//        intentClick.putExtra("title", title);
//        intentClick.putExtra("msg", msg);
//        intentClick.setAction("notification_clicked");
//        intentClick.putExtra(NotificationClickReceiver.TYPE, 0); //0代表点击
//        PendingIntent pendingIntentClick = PendingIntent.getBroadcast(this, id, intentClick, PendingIntent.FLAG_ONE_SHOT);
//
//        Intent intentCancel = new Intent(this, NotificationClickReceiver.class);
//        intentCancel.setAction("notification_cancelled");
//        intentCancel.putExtra(NotificationClickReceiver.TYPE, 1); //1代表清除的监听
//        PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(this, id, intentCancel, PendingIntent.FLAG_ONE_SHOT);

        if (Build.VERSION.SDK_INT >= 26) {  //判断8.0，若为8.0型号的手机进行创下一下的通知栏
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_HIGH);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
            Notification.Builder builder = new Notification.Builder(context, "channel_id");
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setAutoCancel(true);
//                    .setContentIntent(pendingIntentClick)
//                    .setDeleteIntent(pendingIntentCancel);
            manager.notify(id, builder.build());
        } else {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setAutoCancel(true);
//                    .setContentIntent(pendingIntentClick)
//                    .setDeleteIntent(pendingIntentCancel);;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                manager.notify(id, builder.build());
            }
        }
    }

    @Override
    public void onMessage(Context context, Intent intent) {
        try {

            //接收广播：系统启动完成后运行程序
            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
            {
                Intent ootStartIntent = new Intent(context, UPushModule.class);
                ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ootStartIntent);
            }
            //接收广播：安装更新后，自动启动自己。
            if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED) || intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED))
            {
                Intent ootStartIntent = new Intent(context, UPushModule.class);
                ootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ootStartIntent);
            }
            Intent data = new Intent(intent);
//            data.setClass(context, DialogActivity.class);
//            data.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//需为Intent添加Flag：Intent.FLAG_ACTIVITY_NEW_TASK，否则无法启动Activity。
//            context.startActivity(data);
            //可以通过MESSAGE_BODY取得消息体
            //LogUtil.e("YouMengPushIntentService", "onMessage");
            Log.e(TAG, "onMessage");
            final String message = intent.getStringExtra("body");
            if (TextUtils.isEmpty(message)) {
                return;
            }
            final UMessage msg = new UMessage(new JSONObject(message));
            getNotification(context, msg.title, msg.text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
