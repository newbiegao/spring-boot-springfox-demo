package com.plateno.mysrpingboot.web.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Service
public class MyInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // TODO Auto-generated method stub
    logger.info(" HANDLE前拦截器执行");
    return true ;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    // TODO Auto-generated method stub
    logger.info(" HANDLE后拦截器执行");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    // TODO Auto-generated method stub
    logger.info(" HANDLE 结束 拦截器执行");
  }

}
