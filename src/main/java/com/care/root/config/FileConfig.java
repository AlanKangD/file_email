package com.care.root.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//빈을 xml에 사용할 때 상단의 경로로 사용됩니다.

@Configuration //빈을 등록하기전에의 어노테이션입니다. xml에서 설정하는것을 class에서도 사용이 가능합니다.
public class FileConfig {
	@Bean //빈을 등록할 때 사용합니다. 
	public CommonsMultipartResolver multipartResolver() {
		//이름은 꼭 CommonsMultipartResolver multipartResolver() 이렇게 만들어줘야합니다.
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setMaxUploadSize(52428800); //파일 업로드할때 크기 설정 50MB
		mr.setDefaultEncoding("utf-8"); //인코딩 설정입니다.
		
		return mr;
		
	}
	
	
}
 