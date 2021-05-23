package com.huatec.hiot_cloud.data;

import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 网络请求封装类
 */
public class DataManager {
    private NetService service;
    SharedPreferencesHelper sharedPreferencesHelper;

    @Inject
    public DataManager(NetService service, SharedPreferencesHelper sharedPreferencesHelper) {
        this.service = service;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    /**
     * 登入
     *
     * @param userName
     * @param password
     * @return
     */

    public Observable<ResultBase<LoginResultDTO>> login(String userName, String password) {
        return service.login(userName, password, Constants.LOGIN_CODE_APP)
                .doOnNext(new Consumer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void accept(ResultBase<LoginResultDTO> resultBase) throws Exception {
                        if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                            if (resultBase != null && resultBase.getData() != null) {
                                sharedPreferencesHelper.setUserToken(resultBase.getData().getTokenValue());
                            }
                        }
                    }
                });
    }

    /**
     * 获取用户信息
     *
     * @param authorizaton
     * @return
     */

    public Observable<ResultBase<UserBean>> getUserInfo(String authorizaton) {
        return service.getUserInfo(authorizaton);
    }

    /**
     * 修改邮箱
     *
     * @param authorizaton
     * @param email
     * @return
     */

    public Observable<ResultBase<String>> updateEmail(String authorizaton, String email) {
        return service.updateEmail(authorizaton, email);
    }

    /**
     * 注册
     *
     * @param userName 用户名
     * @param password 密码
     * @param email    邮箱地址
     * @return
     */

    public Observable<ResultBase<UserBean>> register(String userName, String password, String
            email) {
        UserBean userBean = new UserBean();
        userBean.setUsername(userName);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setUserType(Constants.REGISTER_TYPE_NORMAL);
        return service.register(userBean);
    }
}




