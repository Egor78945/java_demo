package com.example.transaction_service.repository;

import com.example.transaction_service.model.account.type.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}
