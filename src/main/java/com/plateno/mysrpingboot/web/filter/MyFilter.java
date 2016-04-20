package com.plateno.mysrpingboot.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义过滤器
 * @author gaolk
 *
 */
@WebFilter( filterName="MyFilter" , urlPatterns="/*")
public class MyFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);
  
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    
    logger.info("MyFilter 拦截器启动......");
    
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
    logger.info("请求地址:{}" , ((HttpServletRequest)request).getRequestURL());
    chain.doFilter(request, response);
    
  }

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
    
  }

}
