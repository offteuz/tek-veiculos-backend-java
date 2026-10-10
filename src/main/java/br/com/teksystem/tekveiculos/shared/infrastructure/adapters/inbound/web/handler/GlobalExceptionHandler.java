package br.com.teksystem.tekveiculos.shared.infrastructure.adapters.inbound.web.handler;

import br.com.teksystem.tekveiculos.shared.domain.exception.BusinessException;
import br.com.teksystem.tekveiculos.shared.domain.exception.ConflictException;
import br.com.teksystem.tekveiculos.shared.domain.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String URI_BASE_ERROS = "https://api.tekveiculos.com.br/erros";
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // ========== Specific

    // Tratamento para recursos não encontrados (HTTP 404)
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFound(NotFoundException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Resource Not Found");
        problem.setType(URI.create(URI_BASE_ERROS + "/resource-not-found"));
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Tratamento para conflitos de negócio / dados já cadastrados (HTTP 409)
    @ExceptionHandler(ConflictException.class)
    public ProblemDetail handleConflict(ConflictException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle("Business Rule Conflict");
        problem.setType(URI.create(URI_BASE_ERROS + "/business-rule-conflict"));
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Tratamento genérico para violações de domínio (HTTP 422)
    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        problem.setTitle("Inviolable Business Rule");
        problem.setType(URI.create(URI_BASE_ERROS + "/inviolable-business-rule"));
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // ========== General

    // Tratamento para validações de DTO do Jakarta Validation (@Valid - HTTP 400)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "One or more fields contain syntactic validation errors"
        );
        problem.setTitle("Request Validation Error");
        problem.setType(URI.create(URI_BASE_ERROS + "/invalid-request"));
        problem.setProperty("timestamp", Instant.now());

        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid Value",
                        (existMessage, newMessage) -> existMessage + "; " + newMessage
                ));

        problem.setProperty("invalidFields", fieldErrors);

        return ResponseEntity.status(status).headers(headers).body(problem);
    }

    // Tratamento para violação de constraints no Banco (Chave única, FKs do PostgreSQL)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        log.warn("Persistence attempt with integrity constraint break: {}", ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                "The operation could not be completed due to a registration integrity restriction"
        );
        problem.setTitle("Violation of Relational Integrity");
        problem.setType(URI.create(URI_BASE_ERROS + "/data-integrity-violation"));
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Fallback final para falhas inesperadas de servidor (HTTP 500)
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUncaught(Exception ex, HttpServletRequest request) {
        log.error("Internal uncataloged error caught at HTTP edge", ex);

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An internal processing error occurred on the server"
        );
        problem.setTitle("Internal Server Error");
        problem.setType(URI.create(URI_BASE_ERROS + "/internal-server-error"));
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}
