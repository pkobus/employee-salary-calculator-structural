package pl.patikod.employeesalarycalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patikod.employeesalarycalculator.model.EmployeeTimeSheet;

@Repository
public interface EmployeeTimeSheetRepository extends JpaRepository<EmployeeTimeSheet, Long> {

    EmployeeTimeSheet findActualEmployeeTimeSheetByEmployeeId(Long employeeId);

}
