package pl.patikod.employeesalarycalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.patikod.employeesalarycalculator.model.Employee;
import pl.patikod.employeesalarycalculator.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class EmployeeSalaryCalculatorServiceImpl implements EmployeeSalaryCalculatorService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeSalaryCalculatorServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public BigDecimal calculateSalary(Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        return employee.calculateSalary();
    }

}
