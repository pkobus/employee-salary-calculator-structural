package pl.patikod.employeesalarycalculator.service;

import java.math.BigDecimal;

public interface EmployeeSalaryCalculatorService {

    BigDecimal calculateSalary(Long employeeId);

}
