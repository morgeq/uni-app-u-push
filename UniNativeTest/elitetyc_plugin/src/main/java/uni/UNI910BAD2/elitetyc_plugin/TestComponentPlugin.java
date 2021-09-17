package uni.UNI910BAD2.elitetyc_plugin;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * Created by elitetyc on 2020/5/16.
 * Describe:
 */
public class TestComponentPlugin extends WXComponent<TextView> {

    public TestComponentPlugin(WXSDKInstance instance, WXVContainer parent, BasicComponentData basicComponentData) {
        super(instance, parent, basicComponentData);
    }


    /**
     *构建component的view时会回调此函数
     * @param context
     * @return
     */
    @Override
    protected TextView initComponentHostView(@NonNull Context context) {
        TextView textView = new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setTextSize(30);
        return textView;
    }


    /**
     * 配置参数名
     * @param telNumber
     */
    @WXComponentProp(name = "tel")
    public void setTel(String telNumber) {
        getHostView().setText("tyc联系电话: " + telNumber);
    }


    /**
     * 清除联系电话
     */
    @JSMethod
    public void clearTel() {
        getHostView().setText("tyc联系电话: 电话已清除");
    }


    /**
     * 原生调用uniapp中的方法
     */
    @JSMethod
    public void showTel(){
        String str = getHostView().getText().toString();
//原生触发fireEvent 自定义事件onTel
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> number = new HashMap<>();
        number.put("tel", "我是android返回的:"+str);
//目前uni限制 参数需要放入到"detail"中 否则会被清理
        params.put("detail", number);
        fireEvent("onTel", params);
    }
}
