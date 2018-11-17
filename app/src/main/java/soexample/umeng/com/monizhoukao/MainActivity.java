package soexample.umeng.com.monizhoukao;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.monizhoukao.adapter.MyAdapter;
import soexample.umeng.com.monizhoukao.bean.Bean;
import soexample.umeng.com.monizhoukao.presenter.Presenter;
import soexample.umeng.com.monizhoukao.view.IView;

public class MainActivity extends AppCompatActivity implements IView<Bean> {

    private ImageView getback;
    private ImageView xinxinwei,xinxin;
    private RecyclerView reView;
    private List<Bean.DataBean> list;
    private MyAdapter mAdapter;
    private Presenter mPresenter;
    private String url="http://www.xieast.com/api/news/news.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();

        getback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
//   这是空园的点击事件
    public void smin(View view) {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        float screenWidth =  (width / density);  // 屏幕宽度(dp)
        float screenHeight =  (height / density);// 屏幕高度(dp)
//        xinxinwei是空园控件的id
        ObjectAnimator translationY = ObjectAnimator.ofFloat(xinxinwei, "translationY", new float[]{0f,screenHeight});
        translationY.setDuration(5000);
        translationY.start();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(xinxinwei, "translationX", new float[]{0f,-screenWidth});
        translationX.setDuration(5000);
        translationX.start();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(xinxinwei, "alpha", new float[]{1.0f, 0.8f, 0.6f, 0.2f, 0f});
        alpha.setDuration(5000);
        alpha.start();
        translationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
//                xinxin是黑心控件的id
                xinxin.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private void initAdapter() {
        list = new ArrayList<>();
        mAdapter = new MyAdapter(this, list);
        reView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reView.setLayoutManager(layoutManager);

        mPresenter = new Presenter();
        mPresenter.attch(this);
        mPresenter.get(url);
    }

    private void initView() {
        getback =  findViewById(R.id.getback);
        xinxinwei =  findViewById(R.id.xinxinwei);

        reView =  findViewById(R.id.ReView);
        xinxin=findViewById(R.id.xinxin);
    }

    @Override
    public void success(Bean bean) {
        if(bean!=null){
            List<Bean.DataBean> data = bean.getData();
            if(data!=null){
                list.clear();
                list.addAll(data);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,"报错了"+e,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dele();
    }
}
