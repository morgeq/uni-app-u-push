package uni.UNI910BAD2.elitetyc_plugin;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import static com.taobao.accs.AccsClientConfig.getContext;
//import static uni.UNI910BAD2.elitetyc_plugin.YouMengPushIntentService;
import static uni.UNI910BAD2.elitetyc_plugin.YouMengPushIntentService.*;

import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.common.UniDestroyableModule;
import uni.UNI910BAD2.elitetyc_plugin.helper.PushHelper;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.bridge.JSCallback;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import uni.UNI910BAD2.elitetyc_plugin.YouMengPushIntentService;
public class UPushModule extends UniDestroyableModule {
    private final  String TAG = "UPushModule===>>";
    private Object JSONObject;
    public static Context uniContext = null;
    public static Context uniContext2 = null;
    public void updatePluginStatu() {

        uniContext = mWXSDKInstance.getContext();
        //uniContext2 = YouMengPushIntentService.getContext();
    }
    @UniJSMethod(uiThread = true)
    public void setLoggerEnable(boolean enable) {
        updatePluginStatu();
        //日志开关
        UMConfigure.setLogEnabled(true);
    }


    private boolean hasAgreedAgreement() {
        return MyPreferences.getInstance(mWXSDKInstance.getContext()).hasAgreePrivacyAgreement();
    }

    private void handleAgreement() {
        //if (!hasAgreedAgreement()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(uniContext2);
            builder.setTitle(R.string.agreement_title);
            builder.setMessage(R.string.agreement_msg);
            builder.setPositiveButton(R.string.agreement_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //用户点击隐私协议同意按钮后，初始化PushSDK
                    MyPreferences.getInstance(mWXSDKInstance.getContext()).setAgreePrivacyAgreement(true);
                    PushHelper.init(mWXSDKInstance.getContext());
                }
            });
            builder.setNegativeButton(R.string.agreement_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //finish();
                }
            });
            builder.create().show();
        //}
    }



    @UniJSMethod(uiThread = true)
    public void addCustomMessageListener(JSCallback callback) {
        updatePluginStatu();
        Log.e(TAG,"调用了addCustomMessageListener");
        //updatePluginStatu();
        PushHelper.pushAdvancedFunction(mWXSDKInstance.getContext());
        if (callback != null) {
            Log.e(TAG,"调用了addCustomMessageListener123");
            /*JLogger.w("addCustomMessageListener");
            JPushHelper.eventCallback.put(JConstants.CUSTOM_MESSAGE_EVENT, callback);*/
            //PushHelper.pushAdvancedFunction(mWXSDKInstance.getContext());
            //jsonObject.put("res",res);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("res","123");
            callback.invoke(jsonObject);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @UniJSMethod(uiThread = true)
    public void init(JSONObject options,JSCallback callback){
        updatePluginStatu();
        Log.e(TAG,"调用了init");
        //handleAgreement();
        //用户点击隐私协议同意按钮后，初始化PushSDK
        //MyPreferences.getInstance(mWXSDKInstance.getContext()).setAgreePrivacyAgreement(true);
        Log.e(TAG, "12");
        //建议在线程中执行初始化

        //PushHelper.init(mWXSDKInstance.getContext());
        //JSONObject jsonObject = PushHelper.init(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res","123");
        Log.e(TAG, jsonObject.toJSONString(JSONObject));

        callback.invoke(JSONObject);
    }
    @UniJSMethod(uiThread = true)
    public void delayInit(){
        updatePluginStatu();
        Log.e(TAG,"调用了delayInit");
        //用户点击隐私协议同意按钮后，初始化PushSDK
        MyPreferences.getInstance(mWXSDKInstance.getContext()).setAgreePrivacyAgreement(true);
        PushHelper.init(mWXSDKInstance.getContext());
    }

    @Override
    public void destroy() {

    }
}
