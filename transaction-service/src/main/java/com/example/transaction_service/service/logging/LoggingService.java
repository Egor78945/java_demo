package com.example.transaction_service.service.logging;

/**
 * Интерфейс, предоставляющий функционал для логгирования
 * @param <L> Тип, являющийся объектом логгирования
 */
public interface LoggingService<L> {
    /**
     * Провести логгирование
     * @param loggingSubject Объект логгирования
     */
    void log(L loggingSubject);
}
