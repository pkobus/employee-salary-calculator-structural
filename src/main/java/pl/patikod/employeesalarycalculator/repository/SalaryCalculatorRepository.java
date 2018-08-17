package pl.patikod.employeesalarycalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patikod.employeesalarycalculator.model.EmployeeTimeSheet;
import pl.patikod.employeesalarycalculator.model.salarycalculation.SalaryCalculator;

@Repository
public interface SalaryCalculatorRepository extends JpaRepository<SalaryCalculator, Long> {

}
