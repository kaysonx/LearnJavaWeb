package me.utlight.spring.ioc4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//������ ������
@Configuration
@ComponentScan(basePackages="me.utlight.spring.ioc4")
public class ApplicationCfg {
	@Bean
	public User getUser(){
		return  new User("����Userʵ���ɹ�������");
	}
}
