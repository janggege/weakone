package shixun.lj.bw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.datas.Datas;

/*
  name:刘江
  data:2019
*/public class Myadapter extends RecyclerView.Adapter<Myadapter.Myviewholder> {
    List<Datas.ResultsBean> list;
    Context context;

    public Myadapter(List<Datas.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam, null, false);
        Myviewholder myviewholder = new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder myviewholder, int i) {
        myviewholder.t1.setText(list.get(i).getType());
        myviewholder.t2.setText(list.get(i).getSource());
        Glide.with(context).load(list.get(i).getUrl()).into(myviewholder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView t1;
        private final TextView t2;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            t1 = itemView.findViewById(R.id.text1);
            t2 = itemView.findViewById(R.id.text2);

        }
    }
}
