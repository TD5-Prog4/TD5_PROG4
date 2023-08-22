package com.example.prog4.service;

import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.repository.EmployeeRepository;
import com.example.prog4.repository.dao.EmployeeManagerDao;
import com.example.prog4.repository.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository repository;
    private com.example.prog4.cnaps.repository.CnapsEmployeeRepository cnapsEmployeeRepository;
    private EmployeeManagerDao employeeManagerDao;


    public Employee getOne(String id) {
        Optional<Employee> base = repository.findById(id);
        if (base.isEmpty()) {
            throw new NotFoundException("Employee.Id=" + id + " was not found.");
        }
        Employee result = base.get();
        Optional<com.example.prog4.cnaps.entity.CnapsEmployee> cnaps =
                cnapsEmployeeRepository.findByEndToEndId(id);
        cnaps.ifPresent(employee -> result.setCnaps(employee.getNumber()));
        return result;
    }

    public List<Employee> getAll(EmployeeFilter filter) {
        Sort sort = Sort.by(filter.getOrderDirection(), filter.getOrderBy().toString());
        Pageable pageable = PageRequest.of(filter.getIntPage() - 1, filter.getIntPerPage(), sort);
        return employeeManagerDao.findByCriteria(
                filter.getLastName(),
                filter.getFirstName(),
                filter.getCountryCode(),
                filter.getSex(),
                filter.getPosition(),
                filter.getEntrance(),
                filter.getDeparture(),
                pageable
        );
    }

    public void saveOne(Employee employee) {
        repository.save(employee);
    }
}