package ua.in.dris4ecoder.springConfigClasses;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Collections;

/**
 * Created by Alex Korneyko on 25.09.2016 20:26.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ua.in.dris4ecoder.controllers.webControllers")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    AbstractJackson2HttpMessageConverter messageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    RequestMappingHandlerAdapter requestMappingHandlerAdapter(AbstractJackson2HttpMessageConverter messageConverter) {
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        requestMappingHandlerAdapter.<MappingJackson2HttpMessageConverter>setMessageConverters(Collections.singletonList(messageConverter));
        return requestMappingHandlerAdapter;
    }


}
