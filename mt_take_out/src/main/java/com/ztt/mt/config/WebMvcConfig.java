package com.ztt.mt.config;

import com.ztt.mt.common.JacksonObjectMapper;
import com.ztt.mt.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> list = Arrays.asList(
                "/employee/login",
                "/employee/logout",
                "/user/sendMsg","/user/login","/user/loginout",
                "/backend/**",
                "/front/**"
        );
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(list);
    }
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0, messageConverter);
    }
}
