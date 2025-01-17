package com.course.system.controller.admin;

import com.alibaba.fastjson.JSON;
import com.course.server.dto.*;
import com.course.server.service.UserService;
import com.course.server.util.UuidUtil;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author JT
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    public static final String BUSINESS_NAME = "用户";

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        userService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 新增，id由前端传入
     */
    @PostMapping("/insert")
    public ResponseDto insert(@RequestBody UserDto userDto) {
        mdAndValidator(userDto);
        ResponseDto responseDto = new ResponseDto();
        userService.insert(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }

    /**
     * 更新，如果id的对象找不到则返回失败
     */
    @PostMapping("/update")
    public ResponseDto update(@RequestBody UserDto userDto) {
        mdAndValidator(userDto);
        ResponseDto responseDto = new ResponseDto();
        boolean result = userService.update(userDto);
        if(result){
            responseDto.setContent(userDto);
        }else {
            responseDto.setSuccess(false);
        }
        return responseDto;
    }

    /**
     * 重置密码
     */
    @PostMapping("/save-password")
    public ResponseDto savePassword(@RequestBody UserDto userDto) {
        //二次md5加密
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        ResponseDto responseDto = new ResponseDto();
        userService.savePassword(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        userService.delete(id);
        return responseDto;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseDto login(@RequestBody UserDto userDto, HttpServletRequest request) {
        LOG.info("用户登录开始");
        //二次md5加密
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        ResponseDto responseDto = new ResponseDto();
        // 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
        //String imageCode = (String) request.getSession().getAttribute(userDto.getImageCodeToken());
        String imageCode = (String) redisTemplate.opsForValue().get(userDto.getImageCodeToken());
        LOG.info("从redis中获取到的验证码：{}", imageCode);
        if (StringUtils.isEmpty(imageCode)) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码已过期");
            LOG.info("用户登录失败，验证码已过期");
            return responseDto;
        }
        if (!imageCode.toLowerCase().equals(userDto.getImageCode().toLowerCase())) {
            responseDto.setSuccess(false);
            responseDto.setMessage("验证码不对");
            LOG.info("用户登录失败，验证码不对");
            return responseDto;
        } else {
            // 验证通过后，移除验证码
            //request.getSession().removeAttribute(userDto.getImageCodeToken());
            redisTemplate.delete(userDto.getImageCodeToken());
        }

        LoginUserDto loginUserDto = userService.login(userDto);
        String token = UuidUtil.getShortUuid();
        loginUserDto.setToken(token);
//        request.getSession().setAttribute(Constants.LOGIN_USER, loginUserDto);
        redisTemplate.opsForValue().set(token, JSON.toJSONString(loginUserDto), 10, TimeUnit.HOURS);
        responseDto.setContent(loginUserDto);
        return responseDto;
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout/{token}")
    public ResponseDto logout(@PathVariable String token) {
        ResponseDto responseDto = new ResponseDto();
//        request.getSession().removeAttribute(Constants.LOGIN_USER);
        redisTemplate.delete(token);
        LOG.info("从redis中删除token:{}", token);
        return responseDto;
    }


    /**
     * 公共加密和字段校验方法
     * @param userDto
     */
    private void mdAndValidator (UserDto userDto){
        //二次md5加密
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        // 保存校验
        ValidatorUtil.length(userDto.getName(), "昵称", 1, 50);
        ValidatorUtil.require(userDto.getLoginName(), "账号名");
        ValidatorUtil.length(userDto.getLoginName(), "账号名", 1, 50);
        ValidatorUtil.require(userDto.getPassword(), "密码");
    }
}
