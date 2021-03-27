package com.course.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author JT
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.course")
@MapperScan("com.course.server.mapper")
public class SystemApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SystemApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(SystemApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("启动成功！");
		LOG.info("System本地地址：\thttp://localhost:{}", env.getProperty("server.port"));
		LOG.info("System外网地址：\thttp://{}:{}", InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
	}

}


