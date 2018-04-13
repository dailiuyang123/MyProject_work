package com.daily.common;

import com.daily.util.FileUtils;
import com.daily.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by json on 2018/3/27.
 * Describe:
 */
public class PowerFilter implements Filter {

    @Autowired
    private JedisPool jedisPool;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String method = httpRequest.getMethod(); //请求方式
        String url = httpRequest.getRequestURL().toString();
        System.out.println("请求路径："+url);
        System.out.println("请求URL: "+httpRequest.getRequestURI());
        String origin = httpRequest.getHeader("Origin");
        String header1 = httpRequest.getHeader("Content-Type");
        System.out.println(origin);
        if ("get".equalsIgnoreCase(method)){     //get 方式请求
            chain.doFilter(httpRequest, response);
            return ;
        }else if ("post".equalsIgnoreCase(method)){                             //post方式请求
            String header = httpRequest.getHeader("Content-Type");
            if (header==null||header.indexOf("multipart/form-data")!=-1){
                chain.doFilter(httpRequest,response);
            }
//            ServletInputStream inputStream = httpRequest.getInputStream();
//            byte[] bytes = FileUtils.readFromStream(inputStream);
//            String charEncoding = httpRequest.getCharacterEncoding();
//            if (charEncoding == null) {
//                charEncoding = "UTF-8";
//            }
//            String str = new String(bytes, charEncoding);
//            System.out.println(str);
            Jedis jedis = RedisUtil.getJedis();
            chain.doFilter(request,response);
//            if (token!=null){
//                Boolean exists = jedis.exists(token);
//                if (exists){
//                    //拦截 放行
//                    chain.doFilter(httpRequest,response);
//                }else {
//                    return;
//                }
//            }else {
//                return;
//            }

        }else if ("OPTIONS".equalsIgnoreCase(method)){  //解决跨域问题
            HttpServletResponse response1 = (HttpServletResponse) response;
            response1.setHeader("Access-Control-Allow-Origin", origin);
            chain.doFilter(request,response1);
        }



    }

    @Override
    public void destroy() {

    }
}
