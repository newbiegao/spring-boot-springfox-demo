package com.plateno.mysrpingboot.web.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

public class TomcatConnectorCustomizerConfig implements TomcatConnectorCustomizer {

  // 设置TOMCAT 连接属性
  @Override
  public void customize(Connector connector) {
      
    connector.setAttribute("acceptCount", 1000);
    connector.setAttribute("connectionTimeout", 30000);
    connector.setAttribute("maxThreads", 500);
    
    
    
  }

}
