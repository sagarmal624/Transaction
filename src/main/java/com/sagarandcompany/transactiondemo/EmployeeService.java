package com.sagarandcompany.transactiondemo;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Employee save(Employee employee) {
        employeeRepository.save(employee);
        test(employee);
        return employee;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void test(Employee employee) {
        if (employee.getSalary() > 5000) {
            throw new InvalidOutputException("Invalid output:Salary");
        }
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    public ResponseDTO get(Long id) {
        ResponseDTO responseDTO = new ResponseDTO(false, "REcord Not found");

        return responseDTO;
    }

    public Employee findByName(String name) {
        Optional<Employee> optional = employeeRepository.findByName(name);
        Optional<Employee> optiona2 = employeeRepository.findByName(name);
        Optional<Employee> optiona3 = employeeRepository.findByName(name);
        return optional.get();
    }

    public List<Employee> findBySalary(Integer min, Integer max) {
        return employeeRepository.findAllBySalaryBetweenOrderByNameAsc(min, max);
    }

    public String delete(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent())
            employeeRepository.deleteById(id);

        return "Deleted Successfully";
    }

}

