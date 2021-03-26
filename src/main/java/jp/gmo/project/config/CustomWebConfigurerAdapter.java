package jp.gmo.project.config;

import jp.gmo.project.interceptor.LogInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@AllArgsConstructor
public class CustomWebConfigurerAdapter implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
    }
}
