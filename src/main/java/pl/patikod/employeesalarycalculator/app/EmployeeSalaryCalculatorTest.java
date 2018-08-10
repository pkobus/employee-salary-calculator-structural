package pl.patikod.employeesalarycalculator.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import pl.patikod.employeesalarycalculator.service.EmployeeSalaryCalculatorService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Slf4j
@Component
public class EmployeeSalaryCalculatorTest {

    private EmployeeRepository employeeRepository;
    private EmployeeFixedSalaryRepository employeeFixedSalaryRepository;
    private EmployeeHourlyRateRepository employeeHourlyRateRepository;
    private EmployeeTimeSheetRepository employeeTimeSheetRepository;
    private EmployeeSellingValueRepository employeeSellingValueRepository;
    private EmployeeProvisionValueRepository employeeProvisionValueRepository;
    private EmployeeSalaryCalculatorService employeeSalaryCalculatorService;

    @Autowired
    public EmployeeSalaryCalculatorTest(EmployeeRepository employeeRepository, EmployeeFixedSalaryRepository employeeFixedSalaryRepository, EmployeeHourlyRateRepository employeeHourlyRateRepository, EmployeeTimeSheetRepository employeeTimeSheetRepository, EmployeeSellingValueRepository employeeSellingValueRepository, EmployeeProvisionValueRepository employeeProvisionValueRepository, EmployeeSalaryCalculatorService employeeSalaryCalculatorService) {
        this.employeeRepository = employeeRepository;
        this.employeeFixedSalaryRepository = employeeFixedSalaryRepository;
        this.employeeHourlyRateRepository = employeeHourlyRateRepository;
        this.employeeTimeSheetRepository = employeeTimeSheetRepository;
        this.employeeSellingValueRepository = employeeSellingValueRepository;
        this.employeeProvisionValueRepository = employeeProvisionValueRepository;
        this.employeeSalaryCalculatorService = employeeSalaryCalculatorService;
    }

    @PostConstruct
    public void init() {
        Employee janNowak = janNowak();
        BigDecimal janNowakSalary = employeeSalaryCalculatorService.calculateSalary(janNowak.getId());
        log.info("{} salary = {}", janNowak, janNowakSalary);
        Employee patrykKobus = patrykKobus();
        BigDecimal patrykKobusSalary = employeeSalaryCalculatorService.calculateSalary(patrykKobus.getId());
        log.info("{} salary = {}", patrykKobus, patrykKobusSalary);
        Employee januszKowalski = januszKowalski();
        BigDecimal januszKowalskiSalary = employeeSalaryCalculatorService.calculateSalary(januszKowalski.getId());
        log.info("{} salary = {}", januszKowalski, januszKowalskiSalary);
    }

    private Employee janNowak() {
        Employee employee = new Employee();
        employee.setName("JAN");
        employee.setSurname("NOWAK");
        employee.setSalaryCalculationType(SalaryCalculationType.FIXED);
        employee = employeeRepository.save(employee);
        EmployeeFixedSalary employeeFixedSalary = new EmployeeFixedSalary();
        employeeFixedSalary.setEmployee(employee);
        employeeFixedSalary.setSalary(new BigDecimal("1500"));
        employeeFixedSalaryRepository.save(employeeFixedSalary);
        return employee;
    }

    private Employee patrykKobus() {
        Employee employee = new Employee();
        employee.setName("PATRYK");
        employee.setSurname("KOBUS");
        employee.setSalaryCalculationType(SalaryCalculationType.HOURLY);
        employee = employeeRepository.save(employee);
        EmployeeHourlyRate employeeHourlyRate = new EmployeeHourlyRate();
        employeeHourlyRate.setEmployee(employee);
        employeeHourlyRate.setRate(new BigDecimal("15"));
        employeeHourlyRateRepository.save(employeeHourlyRate);
        EmployeeTimeSheet timeSheet = new EmployeeTimeSheet();
        timeSheet.setEmployee(employee);
        timeSheet.setHours(168);
        employeeTimeSheetRepository.save(timeSheet);
        return employee;
    }

    private Employee januszKowalski() {
        Employee employee = new Employee();
        employee.setName("JANUSZ");
        employee.setSurname("KOWALSKI");
        employee.setSalaryCalculationType(SalaryCalculationType.PERCENTAGE);
        employee = employeeRepository.save(employee);


        EmployeeProvisionValue employeeProvisionValue = new EmployeeProvisionValue();
        employeeProvisionValue.setEmployee(employee);
        employeeProvisionValue.setProvision(new BigDecimal("20"));
        employeeProvisionValueRepository.save(employeeProvisionValue);

        EmployeeSellingValue employeeSellingValue = new EmployeeSellingValue();
        employeeSellingValue.setEmployee(employee);
        employeeSellingValue.setSellingValue(new BigDecimal("20000"));
        employeeSellingValueRepository.save(employeeSellingValue);
        return employee;
    }
}
