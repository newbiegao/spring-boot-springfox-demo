package com.plateno.myspringboot.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.plateno.mysrpingboot.WebApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration( classes = WebApp.class)
@WebAppConfiguration
public class MockMvcWebTests {
  
  @Autowired
  private WebApplicationContext webctx ;
  
  private MockMvc mockMvc ;
  
  @Before
  public void setupMock()
  {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webctx).build() ;
  }
  
  @Test
  public void test()
  {
    
  }
  
  
  /**
  
  @Test
  public void testIndex() throws Exception
  {
     ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/srv/hotel"));
     result.andExpect(MockMvcResultMatchers.status().isOk())
     .andExpect(MockMvcResultMatchers.content().string("hello spring boot")) ;
  }
  
  **/
  
  
}
