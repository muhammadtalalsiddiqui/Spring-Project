package com.dataproviderservice.Repository;

import com.dataproviderservice.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> getAllByEnabledTrue();
    Employee findByEmailAddress(String email);
    Employee findByEmailAddressOrName(String email, String name);





}
