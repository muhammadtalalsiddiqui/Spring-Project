package com.dataproviderservice.Repository;

import com.dataproviderservice.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department ,Integer> {
}
