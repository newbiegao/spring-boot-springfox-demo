package com.plateno.mysrpingboot.web.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {

  @Bean
  public RestTemplate restTemplateConfig()
  {
    
    RestTemplate restTemplate = new RestTemplate() ;
    
    // 长连接保持30秒
    PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
    // 总连接数
    pollingConnectionManager.setMaxTotal(500);
    // 同路由的并发数
    pollingConnectionManager.setDefaultMaxPerRoute(500);

    HttpClientBuilder httpClientBuilder = HttpClients.custom();
    
    httpClientBuilder.setConnectionManager(pollingConnectionManager);
    // 重试次数，默认是3次，没有开启
    httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true));
    // 保持长连接配置，需要在头添加Keep-Alive
    httpClientBuilder.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE);

    List<Header> headers = new ArrayList<>();
    headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
    headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
    headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6"));
    headers.add(new BasicHeader("Connection", "keep-alive"));

    httpClientBuilder.setDefaultHeaders(headers);

    HttpClient httpClient = httpClientBuilder.build();

    // httpClient连接配置，底层是配置RequestConfig
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    // 连接超时
    clientHttpRequestFactory.setConnectTimeout(10);
    // 数据读取超时时间，即SocketTimeout
    clientHttpRequestFactory.setReadTimeout(10);
    // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
    clientHttpRequestFactory.setConnectionRequestTimeout(5);
    // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
    // clientHttpRequestFactory.setBufferRequestBody(false);

    // 添加内容转换器
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    messageConverters.add(new FormHttpMessageConverter());
    messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
    messageConverters.add(new MappingJackson2HttpMessageConverter());
    messageConverters.add(new ByteArrayHttpMessageConverter());

    restTemplate = new RestTemplate(messageConverters);
    restTemplate.setRequestFactory(clientHttpRequestFactory);
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
    
    return restTemplate ;
    
  }
  
}
