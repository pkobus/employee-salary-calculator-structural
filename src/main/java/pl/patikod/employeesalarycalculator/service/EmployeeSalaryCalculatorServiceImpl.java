package pl.patikod.employeesalarycalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.patikod.employeesalarycalculator.model.Employee;
import pl.patikod.employeesalarycalculator.model.EmployeeFixedSalary;
import pl.patikod.employeesalarycalculator.model.EmployeeHourlyRate;
import pl.patikod.employeesalarycalculator.model.EmployeeProvisionValue;
import pl.patikod.employeesalarycalculator.model.EmployeeSellingValue;
import pl.patikod.employeesalarycalculator.model.EmployeeTimeSheet;
import pl.patikod.employeesalarycalculator.model.SalaryCalculationType;
import pl.patikod.employeesalarycalculator.repository.EmployeeFixedSalaryRepository;
import pl.patikod.employeesalarycalculator.repository.EmployeeHourlyRateRepository;
import pl.patikod.employeesalarycalculator.repository.EmployeeProvisionValueRepository;
import pl.patikod.employeesalarycalculator.repository.EmployeeRepository;
import pl.patikod.employeesalarycalculator.repository.EmployeeSellingValueRepository;
import pl.patikod.employeesalarycalculator.repository.EmployeeTimeSheetRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class EmployeeSalaryCalculatorServiceImpl implements EmployeeSalaryCalculatorService {

    private EmployeeRepository employeeRepository;
    private EmployeeFixedSalaryRepository employeeFixedSalaryRepository;
    private EmployeeTimeSheetRepository employeeTimeSheetRepository;
    private EmployeeHourlyRateRepository employeeHourlyRateRepository;
    private EmployeeProvisionValueRepository employeeProvisionValueRepository;
    private EmployeeSellingValueRepository employeeSellingValueRepository;

    @Autowired
    public EmployeeSalaryCalculatorServiceImpl(EmployeeRepository employeeRepository,
                                               EmployeeFixedSalaryRepository employeeFixedSalaryRepository,
                                               EmployeeTimeSheetRepository employeeTimeSheetRepository,
                                               EmployeeHourlyRateRepository employeeHourlyRateRepository,
                                               EmployeeProvisionValueRepository employeeProvisionValueRepository,
                                               EmployeeSellingValueRepository employeeSellingValueRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeFixedSalaryRepository = employeeFixedSalaryRepository;
        this.employeeTimeSheetRepository = employeeTimeSheetRepository;
        this.employeeHourlyRateRepository = employeeHourlyRateRepository;
        this.employeeProvisionValueRepository = employeeProvisionValueRepository;
        this.employeeSellingValueRepository = employeeSellingValueRepository;
    }

    @Override
    public BigDecimal calculateSalary(Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        if (isEmployeeFixedSettled(employee)) {
            return calculateFixedSalaryForEmployee(employee);
        } else if (isEmployeeHourlySettled(employee)) {
            return calculateHourlySalaryForEmployee(employee);
        } else if (isEmployeePercentageSettled(employee)) {
            return calculatePercentageSalaryForEmployee(employee);
        }
        throw new UnsupportedOperationException(String.format("Calculation %s is not supported", employee.getSalaryCalculationType()));
    }

    private boolean isEmployeePercentageSettled(Employee employee) {
        return SalaryCalculationType.PERCENTAGE.equals(employee.getSalaryCalculationType());
    }

    private boolean isEmployeeHourlySettled(Employee employee) {
        return SalaryCalculationType.HOURLY.equals(employee.getSalaryCalculationType());
    }

    private boolean isEmployeeFixedSettled(Employee employee) {
        return SalaryCalculationType.FIXED.equals(employee.getSalaryCalculationType());
    }

    private BigDecimal calculatePercentageSalaryForEmployee(Employee employee) {
        EmployeeProvisionValue employeeProvisionValue = employeeProvisionValueRepository.findEmployeeProvisionValueByEmployeeId(employee.getId());
        EmployeeSellingValue employeeSellingValue = employeeSellingValueRepository.findActualEmployeeSellingValueByEmployeeId(employee.getId());
        return employeeSellingValue.getSellingValue().multiply(employeeProvisionValue.getProvision()).divide(new BigDecimal("100"), BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal calculateHourlySalaryForEmployee(Employee employee) {
        EmployeeTimeSheet employeeTimeSheet = employeeTimeSheetRepository.findActualEmployeeTimeSheetByEmployeeId(employee.getId());
        EmployeeHourlyRate employeeHourlyRate = employeeHourlyRateRepository.findEmployeeHourlyRateByEmployeeId(employee.getId());
        return employeeHourlyRate.getRate().multiply(new BigDecimal(employeeTimeSheet.getHours()));
    }

    private BigDecimal calculateFixedSalaryForEmployee(Employee employee) {
        EmployeeFixedSalary employeeFixedSalary = employeeFixedSalaryRepository.findEmployeeFixedSalaryByEmployeeId(employee.getId());
        return employeeFixedSalary.getSalary();
    }

}
