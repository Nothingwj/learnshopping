package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;

public interface IUserService {

    /**
     * 注册接口
     * */

    public ServerResponse register(UserInfo userInfo);

    /**
     * 登录
     */
    public ServerResponse login(String username,String password);

    /**
     * 根据用户名找回密保问题
     */
    public ServerResponse forget_get_question(String username);

    /**
     * 提交问题答案
     */
    public ServerResponse forget_check_answer(String username,String question,String answer);

}
