package soexample.umeng.com.monizhoukao.model;

import java.lang.reflect.Type;

import soexample.umeng.com.monizhoukao.inter.ICallBack;
import soexample.umeng.com.monizhoukao.utils.HttpUtils;

public class Model {
    public void Login(String url, ICallBack callBack, Type type){
        HttpUtils.getInstance().get(url,callBack,type);
    }
}
