package com.example.demo.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.Common.R;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param request
     * @param user
     * @return {@link R }<{@link User }>
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {
        String password = user.getPassword();

        LambdaQueryWrapper<User> UserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        UserLambdaQueryWrapper.eq(User::getName, user.getName());
        User finduser = userService.getOne(UserLambdaQueryWrapper);
        if (finduser == null) {
            return R.error("不存在用户");
        }
        if (!finduser.getPassword().equals(password)) {
            return R.error("登录失败,密码错误");
        }
        request.getSession().setAttribute("employee", finduser);
        return R.success(finduser);
    }

    /**
     * 注册
     * @param request
     * @param user
     * @return {@link R }<{@link User }>
     */
    @PostMapping("/register")
    public R<User> registerUser(HttpServletRequest request, @RequestBody User user) {

        LambdaQueryWrapper<User> UserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        UserLambdaQueryWrapper.eq(User::getName, user.getName());
        User finduser = userService.getOne(UserLambdaQueryWrapper);
        if (finduser != null) {
            return R.error("用户已经存在");
        } else {
            boolean save = userService.save(user);
            if(save) {
                return R.success(user);
            }
            else{
                return R.error("出错了");
            }
        }

    }


}
