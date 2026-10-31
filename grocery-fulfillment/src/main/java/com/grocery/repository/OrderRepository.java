package com.grocery.repository;

import com.grocery.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<CustomerOrder, Long> {
}