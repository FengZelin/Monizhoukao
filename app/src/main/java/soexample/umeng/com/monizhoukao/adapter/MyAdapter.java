package soexample.umeng.com.monizhoukao.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.monizhoukao.R;
import soexample.umeng.com.monizhoukao.bean.Bean;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHodel> {
    private Context mContext;
    private List<Bean.DataBean> list;

    public MyAdapter(Context context, List<Bean.DataBean> list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(mContext,R.layout.item,null);
        ViewHodel hodel = new ViewHodel(view);
        return hodel;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHodel holder, final int position) {
        holder.tv.setText(list.get(position).getTitle());
        Glide.with(mContext).load(list.get(position).getThumbnail_pic_s()).into(holder.iv);
        holder.bacha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dele(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        ImageView iv,bacha;
        TextView tv;

        public ViewHodel(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            bacha=itemView.findViewById(R.id.bacha);
        }
    }

    public void dele(int position){
        list.remove(position);
        notifyItemRemoved(position);
       notifyItemChanged(position);
    }
}
