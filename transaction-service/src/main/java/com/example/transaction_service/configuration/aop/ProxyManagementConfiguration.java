package com.example.transaction_service.configuration.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Класс, ответственный за конфигурацию Прокси
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ProxyManagementConfiguration {
}
