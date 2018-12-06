package com.neuedu.service.impl;

import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    //注册
    @Override
    public ServerResponse register(UserInfo userInfo) {
        //step1:参数的非空校验
        if (userInfo==null){
            return ServerResponse.createServerResponseByError("参数不能为空");
        }
        //step2:校验用户名
        int result=userInfoMapper.checkUsername(userInfo.getUsername());
        if (result>0){
            return ServerResponse.createServerResponseByError("用户名已存在");
        }
        //step3:校验邮箱
        int result1=userInfoMapper.checkEmail(userInfo.getEmail());
        if (result1>0){
            return ServerResponse.createServerResponseByError("邮箱已存在");
        }
        //step4:注册
        userInfo.setRole(Const.RoleEnum.ROLE_CUSTOMER.getCode());
        int count=userInfoMapper.insert(userInfo);
        if (count>0){
            return ServerResponse.createServerResponseBySuccess("注册成功");
        }
        //step5:返回结果
        return ServerResponse.createServerResponseByError("注册失败");
    }

    @Override
    public ServerResponse login(String username, String password) {
        //step1:参数的非空校验
        if(StringUtils.isBlank(username)){
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        if(StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByError("密码不能为空");
        }
        //step2:检查用户名是否存在
        int result=userInfoMapper.checkUsername(username);
        if (result<=0){
            return ServerResponse.createServerResponseByError("用户名不存在");
        }
        //step3:根据用户名和密码查询
        UserInfo userInfo=userInfoMapper.selectUserByUsernameAndPassword(username,password);
        if (userInfo==null){
            return ServerResponse.createServerResponseByError("密码错误");
        }
        //step4:处理结果并返回
        userInfo.setPassword("");
        return ServerResponse.createServerResponseBySuccess(null,userInfo);
    }

    @Override
    public ServerResponse forget_get_question(String username) {
        //step1:参数的非空校验
        if (username==null || username.equals("")){
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        //step2:检查用户名是否存在
        int result=userInfoMapper.checkUsername(username);
        if (result==0){
            return ServerResponse.createServerResponseByError("用户名不存在");
        }
        //step3:查找密保问题
        String question=userInfoMapper.selectQuestionByUsername(username);
        if (question==null || question.equals("")){
            return ServerResponse.createServerResponseByError("密保问题为空");
        }
        return ServerResponse.createServerResponseBySuccess(question);
    }

    @Override
    public ServerResponse forget_check_answer(String username, String question, String answer) {
        //step1:参数的非空校验
        if (username==null || username.equals("")){
            return ServerResponse.createServerResponseByError("用户名不能为空");
        }
        if (question==null || question.equals("")){
            return ServerResponse.createServerResponseByError("问题不能为空");
        }
        if (answer==null || answer.equals("")){
            return ServerResponse.createServerResponseByError("答案不能为空");
        }
        //step2:根据username,question,answer查询
        int result=userInfoMapper.selectByUsernameAndQuestionAndAnswer(username,question,answer);
        if (result==0){
            return ServerResponse.createServerResponseByError("答案错误");
        }
        //step3:服务端生成一个token保存并将token返回给客户端(UUID随机生成唯一字符串)
        String forgetToken=UUID.randomUUID().toString();
        //guava cache 谷歌的guava缓存
        return null;
    }
}
