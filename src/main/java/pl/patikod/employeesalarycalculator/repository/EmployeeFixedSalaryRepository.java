package pl.patikod.employeesalarycalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patikod.employeesalarycalculator.model.EmployeeFixedSalary;

@Repository
public interface EmployeeFixedSalaryRepository extends JpaRepository<EmployeeFixedSalary, Long> {

    EmployeeFixedSalary findEmployeeFixedSalaryByEmployeeId(Long employeeId);

}
