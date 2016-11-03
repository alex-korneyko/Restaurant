package ua.in.dris4ecoder.springConfigClasses;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by Alex Korneyko on 13.10.2016 19:51.
 */
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class, HibernateConfig.class, SecurityConfig.class, FXSpringConfig.class, WebConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true);


        return new Filter[]{encodingFilter};
    }
}
