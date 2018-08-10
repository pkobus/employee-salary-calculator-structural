package pl.patikod.employeesalarycalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patikod.employeesalarycalculator.model.EmployeeHourlyRate;

@Repository
public interface EmployeeHourlyRateRepository extends JpaRepository<EmployeeHourlyRate, Long> {

    EmployeeHourlyRate findEmployeeHourlyRateByEmployeeId(Long employeeId);

}
