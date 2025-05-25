package com.example.transaction_service.repository;

import com.example.transaction_service.model.log.entity.DatasourceErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasourceErrorLogRepository extends JpaRepository<DatasourceErrorLog, Long> {
}
