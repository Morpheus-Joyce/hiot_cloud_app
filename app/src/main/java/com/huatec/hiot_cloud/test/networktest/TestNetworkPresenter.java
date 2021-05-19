package com.huatec.hiot_cloud.test.networktest;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

public class TestNetworkPresenter extends BasePresenter<TestNetworkPackView> {

    @Inject
    DataManager dataManager;

    @Inject
    public TestNetworkPresenter() {
    }

    public void login(String userName, String password) {
        subscrib(dataManager.login(userName, password), new RequestCallback<ResultBase<LoginResultDTO>>() {
            @Override
            public void onNext(ResultBase<LoginResultDTO> resultBaes) {
                if (resultBaes != null && resultBaes.getData() != null) {
                    getView().showToken(resultBaes.data.getTokenValue());
                } else if (resultBaes != null && !TextUtils.isEmpty(resultBaes.getMsg())) {
                    getView().showMessage(resultBaes.getMsg());
                }

            }
        });
    }

    /**
     * 获取用户信息
     *
     * @param authorization
     */
    public void getUserInfo(String authorization) {
        subscrib(dataManager.getUserInfo(authorization), new RequestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> resultBaes) {
                if (resultBaes != null && resultBaes.getData() != null) {
                    UserBean userBean = resultBaes.getData();
                    String str = String.format("用户：%s,email：%s",
                            userBean.getUsername(), userBean.getEmail());
                    getView().showMessage(str);
                } else if (resultBaes != null && !TextUtils.isEmpty(resultBaes.getMsg())) {
                    getView().showMessage(resultBaes.getMsg());
                }
            }
        });
    }

    /**
     * 修改邮箱
     *
     * @param authorization
     * @param email
     */
    public void updateEmail(String authorization, String email) {
        subscrib(dataManager.updateEmail(authorization, email), new RequestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> resultBaes) {
                if (resultBaes != null && !TextUtils.isEmpty(resultBaes.getData())) {
                    String newEmail = resultBaes.getData();
                    getView().showMessage("修改成功，新邮箱" + newEmail);
                }
                if (resultBaes != null && !TextUtils.isEmpty(resultBaes.getMsg())) {
                    getView().showMessage(resultBaes.getMsg());
                }
            }

        });
    }


    /**
     * 注册
     *
     * @param userName
     * @param password
     * @param email
     */
    public void register(String userName, String password, String email) {
        subscrib(dataManager.register(userName, password, email), new RequestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> resultBaes) {
                if (resultBaes != null && resultBaes.getData() != null) {
                    UserBean newUserBean = resultBaes.getData();
                    String userStr = String.format("username：%s,email：%s",
                            newUserBean.getUsername(), newUserBean.getEmail());
                    getView().showMessage(userStr);
                }
                if (resultBaes != null && !TextUtils.isEmpty(resultBaes.getMsg())) {
                    getView().showMessage(resultBaes.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().showMessage(e.getMessage());
            }
        });
    }
}

