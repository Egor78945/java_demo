package com.example.transaction_service.service.aop.aspect.logging;

import com.example.transaction_service.model.log.entity.DatasourceErrorLog;
import com.example.transaction_service.service.logging.LoggingService;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import com.example.transaction_service.service.aop.annotation.LogDatasourceError;

/**
 * Общий аспект, выполняющий действия с логгированием
 */
@Component
@Aspect
public class CommonServiceLoggingAspect {
    private final LoggingService<DatasourceErrorLog> loggingService;

    public CommonServiceLoggingAspect(@Qualifier("datasourceErrorLogService") LoggingService<DatasourceErrorLog> loggingService) {
        this.loggingService = loggingService;
    }

    /**
     * Advice, отлавливающий исключения из методов, помеченных аннотацией {@link LogDatasourceError}
     * @param e Выброшенное методом исключение
     */
    @AfterThrowing(pointcut = "@annotation(com.example.transaction_service.service.aop.annotation.LogDatasourceError)", throwing = "e")
    public void saveDatasourceLogAfterMethodThrowing(Exception e) {
        loggingService.log(new DatasourceErrorLog(e.getClass().getSimpleName(), Arrays.toString(e.getStackTrace()), e.getMessage(), Timestamp.valueOf(LocalDateTime.now())));
    }
}
