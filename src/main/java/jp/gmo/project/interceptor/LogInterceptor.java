package jp.gmo.project.interceptor;

import jp.gmo.project.service.LoggingService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    private final LoggingService loggingService;

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name()) && request.getMethod().equals(HttpMethod.GET.name())) {
            loggingService.logRequest(request, null);
        }

        return true;
    }
}
