package com.course.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author JT
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

/*    @Value("${file.path}")
    private String FILE_PATH;*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/f/**").addResourceLocations("file:" + FILE_PATH);
        registry.addResourceHandler("/f/**").addResourceLocations("file:D:/毕设/file/course/");

        //http://127.0.0.1:9003/file/f/teacher/1.jpg
    }
}