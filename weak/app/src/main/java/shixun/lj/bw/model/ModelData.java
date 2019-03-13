package shixun.lj.bw.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import shixun.lj.bw.datas.Datas;
import shixun.lj.bw.okhttp.OkHttpUtils;

/*
  name:刘江
  data:2019
*/public class ModelData {
    public interface onclick {
        void onclick(Datas datas);
    }

    onclick onclick;

    public void setOnclick(ModelData.onclick onclick) {
        this.onclick = onclick;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String data = (String) msg.obj;
                    Gson gson = new Gson();
                    Datas datas = gson.fromJson(data, Datas.class);
                    Log.i("aaaa", datas + "");
                    onclick.onclick(datas);

                    break;
            }
        }
    };

    public void mdata(int page) {
        String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/" + page;
        OkHttpUtils.getinstance().doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.i("wwww", string);
                Message message = new Message();
                message.what = 1;
                message.obj = string;
                handler.sendMessage(message);
            }
        });

    }

}
