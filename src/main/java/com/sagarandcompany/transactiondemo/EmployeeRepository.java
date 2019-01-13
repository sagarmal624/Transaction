package com.sagarandcompany.transactiondemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Optional<Employee> findByName(String name);

    public List<Employee> findAllBySalaryBetweenOrderByNameAsc(Integer min, Integer max);

    public Optional<Employee> findByNameAndSalary(String name, Integer salary);

    public Optional<Employee> findByNameOrSalary(String name, Integer salary);

}
