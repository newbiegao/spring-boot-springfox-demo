package com.plateno.myspringboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.plateno.mysrpingboot.WebApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration( classes = WebApp.class)
@WebAppConfiguration
public class WebTest {

  @Test
  public void test()
  {
    System.out.println("ok");
  }
  
  
}
