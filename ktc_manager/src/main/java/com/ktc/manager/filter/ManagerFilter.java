package com.ktc.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 余俊锋
 * @date 2020/11/27 18:10
 * @Description
 */
@Component
public class ManagerFilter extends ZuulFilter {
    @Autowired
    Environment environment;
    @Autowired
    JwtUtil jwtUtil;
    /**
     * 执行时机
     * pre:     在进入微服网关之间执行
     * route:   在执行微服务网关时执行
     * post:    在执行微服务网关之后执行
     * error:   在执行微服务出错执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 执行顺序
     * 数字越大,优先级越低
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器
     * true:执行
     * false:不执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器执行逻辑
     * @return 返回null代表放行,访问对应的微服务
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("Zuul过滤器生效啦！");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String auth = request.getHeader("auth");
        String prefix= environment.getProperty("jwt.config.prefix");
        requestContext.getResponse().setContentType("text/html;charset=utf8");
        if (auth==null||!auth.startsWith(prefix)){
            //无权访问
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("你没有访问权限！");
            return  null;
        }
        String token=auth.substring(prefix.length());
        Claims claims = jwtUtil.parseJWT(token);
        if (!claims.get("role").equals("admin")){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("你不是管理员哦！");
            return null;
        }
        return null;
    }
}
