package app.leo.matchmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

import app.leo.matchmanagement.interceptors.TokenInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mappedInterceptor());
    }

    @Bean
    public MappedInterceptor mappedInterceptor() {
        return new MappedInterceptor(
                new String[] {"/**", ""},
                new String[] {"/actuator/**", "/error/**", "/create/organization"},
                tokenInterceptor());
    }

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*");
    }
}