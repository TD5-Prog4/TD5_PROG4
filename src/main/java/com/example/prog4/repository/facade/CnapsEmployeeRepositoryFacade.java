package com.example.prog4.repository.facade;

import com.example.prog4.repository.entity.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface CnapsEmployeeRepositoryFacade {
    Employee findById(String id);
    Employee save(Employee employee);
}