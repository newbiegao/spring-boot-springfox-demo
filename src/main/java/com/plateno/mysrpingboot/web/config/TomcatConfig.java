package com.plateno.mysrpingboot.web.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
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
    
    container.setPort(8080);
    
  }
  
  
  @Bean
  public EmbeddedServletContainerCustomizer containerCustomizer() {
  
    final TomcatConnectorCustomizerConfig config  = new TomcatConnectorCustomizerConfig() ;
    
    return new EmbeddedServletContainerCustomizer() {
  
      @Override
      public void customize(ConfigurableEmbeddedServletContainer container) {
        
        System.out.println(container.getClass() );
        
        if(container instanceof TomcatEmbeddedServletContainerFactory) {
          TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) container;
          containerFactory.addConnectorCustomizers(config);
        }
      };
    };
  }
  

}
