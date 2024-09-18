package com.paymentchain.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymentchain.billing.entity.Invoice;


public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
