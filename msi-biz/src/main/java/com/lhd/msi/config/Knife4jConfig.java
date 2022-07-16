package com.lhd.msi.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lhd
 */
@Configuration
@EnableSwagger2
public class Knife4jConfig {

    @Bean
    public Docket cockpit(){
        return createDocket("系统管理", "sys");
    }

    private Docket createDocket(String groupName, String moduleName) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("").build())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        String.format("%s.%s.controller", this.getClass().getPackage().getName().replace("config", "module"), moduleName)
                ))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
}
