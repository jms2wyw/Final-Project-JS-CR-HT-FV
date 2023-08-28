package com.company.gamestore.repository;

import com.company.gamestore.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByName(String name);
    List<Invoice> findByItemId(int itemId);
}
