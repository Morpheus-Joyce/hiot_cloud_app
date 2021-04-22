package com.huatec.hiot_cloud.base;
/**
 * MVP架构presenter层基类
 */
public class BasePresenter<V extends BaseView> {
    private V view;

    public BasePresenter() {
        this.view = view;
    }

    public void setView(V view){
        this.view = view;
    }

    public V getView(){
        return view;
    }

    public void destroy(){
        if(viewAttached()){
            view = null;
        }
    }

    public boolean viewAttached(){
        return view != null;
    }

}
