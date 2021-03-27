package com.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author JT
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);
//	public static void main(String[] args) {
//		SpringApplication.run(EurekaApplication.class, args);
//	}
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(EurekaApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("启动成功！");
		LOG.info("Eureka本地地址：\thttp://localhost:{}", env.getProperty("server.port"));
		LOG.info("Eureka外网地址：\thttp://{}:{}", InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"));
	}

}
