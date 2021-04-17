package com.course.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author JT
 */
@Component
public class LoginAdminGatewayFilter implements GatewayFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(LoginAdminGatewayFilter.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // 请求地址中不包含/admin/的，为控台请求，进行需要token和权限拦截
        if (path.contains("/admin/")) {
            if (path.contains("/system/admin/user/login")
                    || path.contains("/system/admin/user/logout")
                    || path.contains("/system/admin/kaptcha")) {
                LOG.info("不需要控台登录验证：{}", path);
                return chain.filter(exchange);
            }
            //获取header的token参数
            String token = exchange.getRequest().getHeaders().getFirst("token");
            LOG.info("控台登录验证开始，token：{}", token);
            if (token == null || token.isEmpty()) {
                LOG.info( "token为空，请求被拦截" );
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            Object object = redisTemplate.opsForValue().get(token);
            if (object == null) {
                LOG.warn( "token无效，请求被拦截" );
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            } else {
                LOG.info("控台已登录：{}", object);

                // 增加权限校验，gateway里没有LoginUserDto，所以全部用JSON操作
                LOG.info("接口权限校验，请求地址：{}", path);
                boolean exist = false;
                JSONObject loginUserDto = JSON.parseObject(String.valueOf(object));
                JSONArray requests = loginUserDto.getJSONArray("requests");
                // 遍历所有【权限请求】，判断当前请求的地址是否在【权限请求】里
                for (int i = 0, l = requests.size(); i < l; i++) {
                    String request = (String) requests.get(i);
                    //判断path请求是否包含权限路径，则返回true
                    //如 path=/system/admin/user/list, request=/system/admin/user, 则path包含request
                    if (path.contains(request)) {
                        exist = true;
                        break;
                    }
                }
                if (exist) {
                    LOG.info("权限校验通过");
                } else {
                    LOG.warn("权限校验未通过");
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    // 清除token并重新跳转至登陆界面
                    redisTemplate.delete(token);
                    return exchange.getResponse().setComplete();
                }
                // 请求继续往下走
                return chain.filter(exchange);
            }
        }
        // 请求地址中包含/web/的，为学生端请求，只需要token拦截
        else if (path.contains("/web/")){
            // 请求地址包含web登录注册，退出登录，忘记密码和验证码的，不需要token验证
            if (path.contains("/web/member/register")
                    || path.contains("/web/member/login")
                    || path.contains("/web/member/logout")
                    || path.contains("/web/member/is-mobile-exist")
                    || path.contains("/web/member/reset-password")
                    || path.contains("/web/kaptcha")
                    || path.contains("/web/sms")) {
                LOG.info("不需要学生端登录验证：{}", path);
                return chain.filter(exchange);
            }
            //获取header的token参数
            String token = exchange.getRequest().getHeaders().getFirst("token");
            LOG.info("学生端验证开始，token：{}", token);
            if (token == null || token.isEmpty()) {
                LOG.info( "token为空，请求被拦截" );
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            Object object = redisTemplate.opsForValue().get(token);
            if (object == null) {
                LOG.warn( "token无效，请求被拦截" );
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            } else {
                LOG.info("学生端访问成功：{}", object);
                // 请求继续往下走
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder()
    {
        return 1;
    }
}