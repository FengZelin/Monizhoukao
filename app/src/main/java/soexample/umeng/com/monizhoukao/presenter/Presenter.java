package soexample.umeng.com.monizhoukao.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import soexample.umeng.com.monizhoukao.bean.Bean;
import soexample.umeng.com.monizhoukao.inter.ICallBack;
import soexample.umeng.com.monizhoukao.model.Model;
import soexample.umeng.com.monizhoukao.view.IView;

public class Presenter {
    private Model mModel;
    private IView iv;

    public void attch(IView iv) {
        this.iv = iv;
        mModel=new Model();
    }


    public void get(String url){
        Type type = new TypeToken<Bean>() {
        }.getType();
        mModel.Login(url, new ICallBack() {
            @Override
            public void OnSuccess(Object o) {
               iv.success(o);
            }

            @Override
            public void OnFailed(Exception e) {
                iv.failed(e);
            }
        },type);
    }
    public void dele(){
        if(iv!=null){
            iv=null;
        }
    }
}
