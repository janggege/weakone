package shixun.lj.bw;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import shixun.lj.bw.adapter.Myadapter;
import shixun.lj.bw.datas.Datas;
import shixun.lj.bw.presenter.Presenterdata;
import shixun.lj.bw.view.Viewdata;

public class MainActivity extends AppCompatActivity implements Viewdata {
    private int page = 1;
    private RecyclerView recyclerView;
    private SwipyRefreshLayout swipyRefreshLayout;
    private Handler handler = new Handler();
    private List<Datas.ResultsBean> list;
    private Presenterdata presenterdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recy);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        presenterdata = new Presenterdata(this);
        presenterdata.attachview(this);
        presenterdata.pdata(page);
        swipyRefreshLayout = findViewById(R.id.refresh);
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swipyRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        presenterdata.pdata(page);
                        swipyRefreshLayout.setRefreshing(false);
                    }
                }, 2000);

            }

            @Override
            public void onLoad(int index) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        presenterdata.pdata(page);
                        swipyRefreshLayout.setRefreshing(false);

                    }
                }, 2000);

            }
        });


    }

    @Override
    public void onclick(Datas datas) {
        List<Datas.ResultsBean> results = datas.getResults();
        if (page == 1) {
            list = new ArrayList<Datas.ResultsBean>();

        }
        list.addAll(results);
        Myadapter myadapter = new Myadapter(list, MainActivity.this);
        recyclerView.setAdapter(myadapter);
        //显示当前页面
        recyclerView.scrollToPosition(list.size() - (results.size()));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterdata.detachview();
    }
}
