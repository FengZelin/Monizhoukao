package soexample.umeng.com.monizhoukao.view;

public interface IView<T>
{
    void success(T t);
    void failed(Exception e);
}
