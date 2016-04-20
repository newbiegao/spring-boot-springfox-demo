package com.plateno.myspringboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.plateno.mysrpingboot.WebApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration( classes = WebApp.class)
public class RestTemplateTest {

  @Autowired
  private RestTemplate rest ; 
  
  @Test
  public void restRequest()
  {
    String url = "https://www.baidu.com" ;
    ResponseEntity<String> ret = rest.getForEntity(url, String.class) ;
    System.out.println(ret.getBody()) ;
    
  }
  
  
}
