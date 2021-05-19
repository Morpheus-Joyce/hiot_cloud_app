package com.huatec.hiot_cloud.data;

import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * 网络请求封装类
 */
public class DataManager {
    private NetService service;

    @Inject
    public DataManager(NetService service) {
        this.service = service;

    }

    /**
     * 登入
     *
     * @param userName
     * @param password
     * @return
     */

    public Observable<ResultBase<LoginResultDTO>> login(String userName, String password) {
        return service.login(userName, password, Constants.LOGIN_CODE_APP);

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

    public Observable<ResultBase<UserBean>> register(String userName, String password, String email) {
        UserBean userBean = new UserBean();
        userBean.setUsername(userName);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setUserType(Constants.REGISTER_TYPE_NORMAL);
        return service.register(userBean);
    }
}





