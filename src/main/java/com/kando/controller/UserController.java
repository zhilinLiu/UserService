package com.kando.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.kando.common.exception.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.kando.dto.Result;
import com.kando.entity.User;
import com.kando.service.UserService;
import com.kando.vo.PageVo;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;


/**
 * @author 孙雨佳
 * @ClassName: UserController
 * @Description: 控制器类-登陆注册-用户管理
 * @date 2019年10月18日
 */
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private Long AUTH_CODE_EXPIRE_SECONDS;

    private String authCodeKey = "1";
    //生成验证码
    @RequestMapping(value = "/image", method = RequestMethod.GET)//隐藏接口
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到redis中
            String createText = defaultKaptcha.createText();
            log.debug("image code is:"+createText);
            redisTemplate.opsForValue().set(authCodeKey,createText);
       //   redis.opsForValue().set(email, seccode, 5, TimeUnit.MINUTES);
            String ss = redisTemplate.opsForValue().get(authCodeKey).toString();
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }


    /**
     * @return Result   返回类型
     * @Title: loginByPwd
     * @Description: TODO(登陆操作 - 用户名密码)
     */
    @RequestMapping(value = "/loginByPwd", method = RequestMethod.GET)
    public Result loginByPwd(User user) {
        log.info("is doing loginByPwd.....");
        try {
            Result<User> result = new Result();
            User user1 = userService.loginByPwd(user);
            result.setCode(0);
            result.setMessage("成功");
            result.setToken(user1!=null?userService.generateToken(user1):"Notoken");
            result.setSuccess(true);
            result.setData(user1);
            log.info("登录成功");

            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @Title: loginByCode
     * @Description: TODO(登陆操作 - 发送手机验证码)
     */

    @RequestMapping(value = "/loginByCode", method = RequestMethod.GET)
    public Result loginByCode(@Validated User user,HttpServletResponse httpServletResponse) {
        log.info("is doing loginByCode.....");
        try {
            Result result = userService.loginByCode(user);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            return result;
        }
    }


    /**
     * @return Result    返回类型
     * @Title: loginCheckCode
     * @Description: TODO(登陆操作 - 验证验证码)
     */

    @RequestMapping(value = "/loginCheckCode", method = RequestMethod.GET)
    public Result checkCode(@Validated User user) {
        try {
            User user1 = userService.loginCheckCode(user);
            Result<User> result = new Result();
            result.setCode(0);
            result.setToken(user1!=null?userService.generateToken(user1):"Notoken");
            result.setMessage("成功");
            result.setSuccess(true);
            result.setData(user1);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @Title: indexByCode
     * @Description: TODO(注册操作 - 发送手机验证码)
     */

    @RequestMapping(value = "/indexByCode", method = RequestMethod.GET)
    public Result indexByCode(String phone) {
        try {
            User user = new User();
            user.setPhone(phone);
            Result result = userService.indexByCode(user);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result   返回类型
     * @Title: indexCheckCode
     * @Description: TODO(注册操作 - 验证手机验证码)
     */

    @RequestMapping(value = "/indexCheckCode", method = RequestMethod.POST)
    public Result indexCheckCode(@RequestBody User user) {
        try {
            ResultEnum resultEnum = userService.indexCheckCode(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @throws
     * @Title: indexBindEmail
     * @Description: TODO(注册操作 - 绑定邮箱 - 发送邮箱验证码)
     */

    @RequestMapping(value = "/indexBindEmail", method = RequestMethod.GET)
    public Result indexBindEmail(User user) {
        try {
            Result result = new Result();
            ResultEnum resultEnum = userService.indexBindEmail(user);
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @Title: IndexEmailCode
     * @Description: TODO(注册操作 - 绑定邮箱 - 验证邮箱验证码)
     */

    @RequestMapping(value = "/IndexEmailCode", method = RequestMethod.POST)
    public Result IndexEmailCode(@RequestBody User user) {
        try {
            ResultEnum resultEnum = userService.IndexEmailCode(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result   返回类型
     * @Title: selectUser
     * @Description: TODO(查询用户)
     */

    @RequestMapping(value = "/selectUser", method = RequestMethod.GET)
    public Result selectUser(PageVo pageVo) {
        try{
            Result result = new Result();
            PageInfo<User> pageInfo = userService.selectUser(pageVo);
            if (ObjectUtils.isNotEmpty(pageInfo)) {
                result.setCode(0);
                result.setMessage("查询成功");
                result.setSuccess(true);
                result.setData(pageInfo);
            } else {
                result.setCode(1);
                result.setMessage("查询失败");
                result.setSuccess(false);
            }
            return result;
        }catch (Exception e){
            Result result = new Result();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            result.setCode(1);
            return result;
        }

    }

    /**
     * @return Result    返回类型
     * @Title: deleteUser
     * @Description: TODO(删除用户)
     */

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Result deleteUser(@RequestBody User user) {
        try {
            ResultEnum resultEnum = userService.deleteUser(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @Title: updateUser
     * @Description: TODO(修改用户 - 点击修改)
     */

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public Result updateUser(Integer id) {
        try {
            Result result = new Result();
            User user1 = userService.updateUser(id);
            result.setData(user1);
            result.setCode(0);
            result.setMessage("点击修改");
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @throws
     * @Title: updateUser1
     * @Description: TODO(修改用户 - 修改成功)
     */

    @RequestMapping(value = "/updateUser1", method = RequestMethod.POST)
    public Result updateUser1(@RequestBody User user) {
//        User user = new User();
//        BeanUtils.copyProperties(user1,user);
        try {
            log.info("接收到的参数为"+user);
            ResultEnum resultEnum = userService.updateUser1(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

}