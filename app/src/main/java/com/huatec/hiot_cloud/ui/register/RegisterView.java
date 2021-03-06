package com.huatec.hiot_cloud.ui.register;

import com.huatec.hiot_cloud.ui.base.BaseView;

/**
 * 注册模块view层接口
 */
interface RegisterView extends BaseView {

    /**
     * 注册成功后的处理
     * @param email
     * @param password
     */
    void registerSucc(String email, String password);

    /**
     * 自动登录后的处理
     */
    void loginSucc();
}
