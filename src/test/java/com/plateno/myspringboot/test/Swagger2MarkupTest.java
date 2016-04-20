package com.plateno.myspringboot.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get ;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status ;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.plateno.mysrpingboot.WebApp;

import io.github.robwin.markup.builder.MarkupLanguage;
import springfox.documentation.staticdocs.Swagger2MarkupResultHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration( classes = WebApp.class)
@WebAppConfiguration
public class Swagger2MarkupTest {
  
  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
     
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
     
  }
  
  @Test
  public void convertSwaggerToAsciiDoc() throws Exception {
              
      ResultActions rest = this.mockMvc.perform(get("/v2/api-docs").characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON));
      rest.andReturn().getResponse().setCharacterEncoding("UTF-8") ;
      rest.andDo(Swagger2MarkupResultHandler.outputDirectory("src/docs/asciidoc/generated")
      .withMarkupLanguage(MarkupLanguage.ASCIIDOC).build())
      .andExpect(status().isOk()) ;
  }

  @Test
  public void convertSwaggerToMarkdown() throws Exception {
      this.mockMvc.perform(get("/v2/api-docs?group=user").characterEncoding("UTF-8")
              .accept(MediaType.APPLICATION_JSON))
              .andDo(Swagger2MarkupResultHandler.outputDirectory("src/docs/markdown/generated")
                  .withMarkupLanguage(MarkupLanguage.MARKDOWN).build())
              .andExpect(status().isOk());
  }
  
}
