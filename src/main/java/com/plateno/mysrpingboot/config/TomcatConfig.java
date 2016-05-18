package com.plateno.mysrpingboot.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * TOMCAT 配置
 * @author gaolk
 *
 */
@Configuration
public class TomcatConfig implements EmbeddedServletContainerCustomizer {
  
  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    
    // 对标准属性进行设置
    container.setPort(8080);
    
    // 对TOMCAT非标准属性进行设置
    if( container instanceof TomcatEmbeddedServletContainerFactory )
    {
      
      TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) container;
      containerFactory.addConnectorCustomizers( new TomcatConnectorCustomizer(){

        @Override
        public void customize(Connector connector) {
          
          connector.setAttribute("acceptCount", 1001);
          connector.setAttribute("connectionTimeout", 30001);
          connector.setAttribute("maxThreads", 5001);
          
        }
          
      } );
    }
  }
}
