package uni.UNI910BAD2.elitetyc_plugin;


import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

/**
 * Created by elitetyc on 2020/5/16.
 * Describe:
 */
public class TestModulePlugin extends WXModule {

    private final  String TAG = "elitetyc===>>";

    @JSMethod(uiThread = true)
    public void calcNum(JSONObject options, JSCallback callback){
        Log.e(TAG,"调用了call方法,两个数字相加");
        Integer num1 = options.getInteger("num1");
        Integer num2 = options.getInteger("num2");
        Integer res = num1+num2;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res",res);
        callback.invoke(jsonObject);
    }


    @JSMethod(uiThread = false)
    public void  sleepCalcNum(JSONObject options, JSCallback callback){
        Log.e(TAG,"调用了sleepCalcNum方法,睡眠3秒，两个数字相加");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer num1 = options.getInteger("num1");
        Integer num2 = options.getInteger("num2");
        Integer res = num1+num2;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res",res);
        callback.invoke(jsonObject);
    }

}
