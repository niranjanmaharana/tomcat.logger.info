package com.legato.tomcat.logger.info.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.legato.tomcat.logger.info.config.SpringConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{SpringConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
}