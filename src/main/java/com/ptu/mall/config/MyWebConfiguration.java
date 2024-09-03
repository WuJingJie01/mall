package com.ptu.mall.config;

import com.ptu.mall.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration
@EnableSwagger2
public class MyWebConfiguration extends WebMvcConfigurationSupport {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
            	// 分组的名称
                .groupName("后端接口文档")
                .select()
                // 匹配这个包里的接口
                .apis(RequestHandlerSelectors.basePackage("com.ptu.mall.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("啦啦啦", "https://www.baidu.com", "神仙邮箱");
        return new ApiInfo(
            // 文档名称
            	"Ptu的暑假作业 - mall项目接口文档",
            // 描述
                "ptu的商城大作业",
            // 版本号
                "1.0",
            // 服务器地址
                "localhost:8080",
                contact,
            // 这个就不用改了
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 处理Swagger配置
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // 处理 static 目录下的静态资源
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        // 处理upload目录下的静态资源
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("classpath:/upload/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**/admin/**");
    }
}
