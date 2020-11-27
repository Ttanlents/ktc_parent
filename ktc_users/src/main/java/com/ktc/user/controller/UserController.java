package com.ktc.user.controller;


import com.ktc.user.pojo.User;
import com.ktc.user.service.UserService;
import entity.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @Description 用户 控制器层
 * @date 2020-11-25 17:56:10
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private Environment environment;
    @Autowired
    private BCryptPasswordEncoder encoder;


/**
 * 查询全部数据
 * @return
 */
    @RequestMapping(method = RequestMethod.GET)

    public Result findAll() {
        return new Result(StatusCode.OK, true, "查询成功", userService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(StatusCode.OK, true, "查询成功", userService.findById(id));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return new Result(StatusCode.OK, true, "查询成功", new PageResult<User>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(StatusCode.OK, true, "查询成功", userService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param user
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody User user) {
        userService.add(user);
        return new Result(StatusCode.OK, true, "增加成功");
    }

    /**
     * 修改
     *
     * @param user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        userService.update(user);
        return new Result(StatusCode.OK, true, "修改成功");
    }

    /**
     * 删除   token鉴定权限
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
       /* String auth = request.getHeader("auth");
        if (auth==null||"".equals(auth)){
            return new Result(StatusCode.ACCESS_ERROR, false, "认证错误");
        }
        String prefix = environment.getProperty("jwt.config.prefix");
        if (!auth.startsWith(prefix)){
            return new Result(StatusCode.ACCESS_ERROR, false, "认证有误");
        }
        String token=auth.substring(prefix.length());
        Claims claims = jwtUtil.parseJWT(token); //如果claims为空会直接异常
        if (!claims.get("role").equals("admin")){
            return new Result(StatusCode.ACCESS_ERROR, false, "权限不足");
        }*/
        Claims claimsAdmin =(Claims) request.getAttribute("claims_admin");
        if (claimsAdmin==null){
           return new Result(StatusCode.ACCESS_ERROR, false, "认账有误");
       }
        userService.deleteById(id);
        return new Result(StatusCode.OK, true, "删除成功");
    }

    /**
     * 登录
     * @param user
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        User login = userService.login(user.getMobile());
        if (login==null||!encoder.matches(user.getPassword(),login.getPassword())){
            return new Result(StatusCode.ERROR, false, "账号或密码错误");
        }
        //签发token
        String token = jwtUtil.createJWT(login.getId(), login.getNickname(), "user");
        Map<Object, Object> map = new HashMap<>();
        String auth=environment.getProperty("jwt.config.prefix")+token;
        map.put("auth",auth);
        map.put("loginname",login.getNickname());
        return new Result(StatusCode.OK, true, "登陆成功",map);

    }

    /**
     * 发送手机验证码
     * @param mobile
     */
    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendsms(@PathVariable String mobile) {
        userService.sendsms(mobile);
        return new Result(StatusCode.OK, true, "发送验证码成功");
    }

    /**
     * 用户注册
     * @param user
     * @param code
     * @return
     */
    @RequestMapping(value = "/register/{code}",method = RequestMethod.POST)
    public Result register(@RequestBody User user,@PathVariable String code){
        
        Result result = userService.register(user, code);
        return result;
    }

    /**
     * 更新粉丝数
     * @param userid 要更改的用户id
     * @param count 更改的数量
     * @return
     */
    @RequestMapping(value = "/updateFansCount/{userid}/{count}", method = RequestMethod.PUT)
    public Result updateFansCount(@PathVariable String userid,@PathVariable Integer count) {
        userService.updateFansCount(userid,count);
        return new Result( StatusCode.OK,true, "更新成功");
    }

    /**
     * 更新关注数
     * @param userid 要更改的用户id
     * @param count 更改的数量
     * @return
     */
    @RequestMapping(value = "/updateFollowCount/{userid}/{count}", method = RequestMethod.PUT)
    public Result updateFollowCount(@PathVariable String userid,@PathVariable Integer count) {

        userService.updateFollowCount(userid,count);

        return new Result( StatusCode.OK,true, "更新成功");
    }



}
