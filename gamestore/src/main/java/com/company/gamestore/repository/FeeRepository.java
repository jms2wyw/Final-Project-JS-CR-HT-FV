package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
import com.company.gamestore.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Integer> {

    Optional<Fee> findByProductType(String productType);

}
