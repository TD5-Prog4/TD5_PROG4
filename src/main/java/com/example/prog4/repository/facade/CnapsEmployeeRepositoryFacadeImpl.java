package com.example.prog4.repository.facade;

import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.repository.EmployeeRepository;
import com.example.prog4.repository.entity.Employee;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CnapsEmployeeRepositoryFacadeImpl implements CnapsEmployeeRepositoryFacade {
    private final EmployeeRepository EmployeeRepository;
    private final com.example.prog4.cnaps.repository.CnapsEmployeeRepository cnapsEmployeeRepository;

    public CnapsEmployeeRepositoryFacadeImpl(
            @Qualifier("EmployeeRepository")
            EmployeeRepository EmployeeRepository,
            @Qualifier("cnapsEmployeeRepository")
            com.example.prog4.cnaps.repository.CnapsEmployeeRepository cnapsEmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
        this.cnapsEmployeeRepository = cnapsEmployeeRepository;
    }

    @Override
    public Employee findById(String id) {
        Optional<Employee> base = EmployeeRepository.findById(id);
        if (base.isEmpty()) {
            throw new NotFoundException("Employee.Id=" + id + " was not found");
        }
        Optional<com.example.prog4.cnaps.entity.CnapsEmployee> cnaps =
                cnapsEmployeeRepository.findByEndToEndId(id);
        Employee result;
        result = base.get();
        cnaps.ifPresent(employee -> result.setCnaps(employee.getNumber()));

        return result;
    }

    @Override
    public Employee save(Employee employee) {
        return EmployeeRepository.save(employee);
    }
}
