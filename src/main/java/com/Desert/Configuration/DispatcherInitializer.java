package com.Desert.Configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebMVC.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebMVC.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
