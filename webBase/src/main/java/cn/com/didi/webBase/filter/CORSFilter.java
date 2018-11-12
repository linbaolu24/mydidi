package cn.com.didi.webBase.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class CORSFilter implements Filter {
 
    public FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    // Just for debug with webstorm sending cross domain message
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rsp= (HttpServletResponse) response;

        rsp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        rsp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");//有些人只设置X-Requested-With这是为什么，有时候这样又不起作用
        rsp.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
        rsp.setHeader("Access-Control-Max-Age","3600");
        rsp.setHeader("Access-Control-Allow-Credentials","true");//是否支持cookie跨域
        rsp.addCookie(new Cookie("JSSESIONID",req.getSession().getId()));
        chain.doFilter(request, response);
    }

    public void destroy() {
        this.config = null;
    }
 
}