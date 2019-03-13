package shixun.lj.bw.presenter;

import java.lang.ref.WeakReference;

import shixun.lj.bw.datas.Datas;
import shixun.lj.bw.model.ModelData;
import shixun.lj.bw.view.Viewdata;

/*
  name:刘江
  data:2019
*/public class Presenterdata<T> {

    private final ModelData modelData;
    private final Viewdata viewdata1;
    private WeakReference<T> tWeakReference;

    public Presenterdata(Viewdata viewdata) {
        modelData = new ModelData();
        viewdata1 = viewdata;
    }

    public void pdata(int page) {
        modelData.mdata(page);
        modelData.setOnclick(new ModelData.onclick() {
            @Override
            public void onclick(Datas datas) {
                viewdata1.onclick(datas);

            }
        });

    }

    public void attachview(T t) {
        tWeakReference = new WeakReference<>(t);
    }

    public void detachview() {
        if (tWeakReference != null) {
            tWeakReference.clear();
            tWeakReference = null;
        }
    }

}
