package pl.patikod.employeesalarycalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patikod.employeesalarycalculator.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
