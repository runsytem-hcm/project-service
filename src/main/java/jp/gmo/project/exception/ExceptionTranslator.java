package jp.gmo.project.exception;

import jp.gmo.project.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
import org.zalando.problem.violations.ConstraintViolationProblem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling {

    private static final String FIELD_ERRORS_KEY = "fieldErrors";
    private static final String MESSAGE_KEY = "message";
    private static final String PATH_KEY = "path";


    @Override
    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, @Nonnull NativeWebRequest request) {
        if (entity == null) {
            return null;
        }
        Problem problem = entity.getBody();

        ProblemBuilder builder = Problem.builder()
                .with(PATH_KEY, Objects.requireNonNull(request.getNativeRequest(HttpServletRequest.class)).getRequestURI());

        if (!(problem instanceof DefaultProblem)) {
            builder.with(MESSAGE_KEY, problem != null ? Objects.requireNonNull(problem.getTitle()) : "");
        } else {
            problem.getParameters().forEach(builder::with);

            if (!problem.getParameters().containsKey(MESSAGE_KEY) && problem.getStatus() != null) {
                builder.with(MESSAGE_KEY, Utils.getMessage("rs.error." + problem.getStatus().getStatusCode()));
            }
        }

        builder.with("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
    }

    @Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nonnull NativeWebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors().stream()
                .map(f -> new FieldError(f.getField(), f.getDefaultMessage()))
                .collect(Collectors.toList());

        Problem problem = Problem.builder()
                .withStatus(defaultConstraintViolationStatus())
                .with(MESSAGE_KEY, Utils.getMessage("rs.validate." + defaultConstraintViolationStatus().getStatusCode()))
                .with(FIELD_ERRORS_KEY, fieldErrors)
                .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleBadRequestAlertException(BadRequestAlertException ex, NativeWebRequest request) {
        return create(ex, request);
    }
}