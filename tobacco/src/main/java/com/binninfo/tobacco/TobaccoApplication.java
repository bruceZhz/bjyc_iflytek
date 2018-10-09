package com.binninfo.tobacco;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.binninfo.tobacco.mapper")
public class TobaccoApplication{

	public static void main(String[] args) {
		SpringApplication.run(TobaccoApplication.class, args);
	}

	/**
	 * @description 设置文件上传大小限制为10M
	 * @author wangt
	 * @create 2016年11月11日下午5:34:06
	 * @version 1.0
	 * @return MultipartConfigElement
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(1024 * 1024 * 1024l);
		return factory.createMultipartConfig();
	}

}
