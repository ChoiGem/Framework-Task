package org.tukorea.free.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import jakarta.servlet.Filter;

@Configuration
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	// DispatcherServlet에 매핑할 요청 주소를 지정한다.
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	 
	// 서블릿 빈과 스프링 MVC 속성 값을 설정
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};	
	}
	
	// 서비스와 리파지토리 레이어의 빈 설정
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}
	
	// 파라미터 인코딩 필터 설정(UTF-8)
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] {encodingFilter};
	}
}
