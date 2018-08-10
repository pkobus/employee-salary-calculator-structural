package pl.patikod.employeesalarycalculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeSalaryCalculatorApplicationTests {

	private static final String NAME_JAN = "JAN";
	private static final String NAME_PATRYK = "PATRYK";
	private static final String NAME_JANUSZ = "JANUSZ";
	private static final String SURNAME_NOWAK = "NOWAK";
	private static final String SURNAME_KOBUS = "KOBUS";
	private static final String SURNAME_KOWALSKI = "KOWALSKI";
	private static final BigDecimal HOURLY_RATE_15 = new BigDecimal("15");
	private static final int HOURS_168 = 168;
	private static final BigDecimal PROVISION_20 = new BigDecimal("20");
	private static final BigDecimal SELLING_VALUE_20_000 = new BigDecimal("20000");
	private static final BigDecimal SALARY_1500 = new BigDecimal("1500");
	private static final BigDecimal SALARY_2520 = new BigDecimal("2520");
	private static final BigDecimal SALARY_4000 = new BigDecimal("4000");

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeFixedSalaryRepository employeeFixedSalaryRepository;

	@Autowired
	private EmployeeHourlyRateRepository employeeHourlyRateRepository;

	@Autowired
	private EmployeeTimeSheetRepository employeeTimeSheetRepository;

	@Autowired
	private EmployeeSellingValueRepository employeeSellingValueRepository;

	@Autowired
	private EmployeeProvisionValueRepository employeeProvisionValueRepository;

	@Autowired
	private EmployeeSalaryCalculatorService employeeSalaryCalculatorService;


	@Test
	public void testFixedPricedCalculationSalary() {
		//given
		Employee employee = saveFixedPricedJanNowak();
		//when
		BigDecimal salary = employeeSalaryCalculatorService.calculateSalary(employee.getId());
		//then
		assertThat(salary).isEqualByComparingTo(SALARY_1500);
	}

	@Test
	public void testHourlyRatedCalculationSalary() {
		//given
		Employee employee = saveHourlyRatedPatrykKobus();
		//when
		BigDecimal salary = employeeSalaryCalculatorService.calculateSalary(employee.getId());
		//then
		assertThat(salary).isEqualByComparingTo(SALARY_2520);
	}

	@Test
	public void testProvisionSettledCalculationSalary() {
		//given
		Employee employee = saveProvisionSettledJanuszKowalski();
		//when
		BigDecimal salary = employeeSalaryCalculatorService.calculateSalary(employee.getId());
		//then
		assertThat(salary).isEqualByComparingTo(SALARY_4000);
	}

	private Employee saveFixedPricedJanNowak() {
		Employee employee = new Employee();
		employee.setName(NAME_JAN);
		employee.setSurname(SURNAME_NOWAK);
		employee.setSalaryCalculationType(SalaryCalculationType.FIXED);
		employee = employeeRepository.save(employee);
		EmployeeFixedSalary employeeFixedSalary = new EmployeeFixedSalary();
		employeeFixedSalary.setEmployee(employee);
		employeeFixedSalary.setSalary(SALARY_1500);
		employeeFixedSalaryRepository.save(employeeFixedSalary);
		return employee;
	}

	private Employee saveHourlyRatedPatrykKobus() {
		Employee employee = new Employee();
		employee.setName(NAME_PATRYK);
		employee.setSurname(SURNAME_KOBUS);
		employee.setSalaryCalculationType(SalaryCalculationType.HOURLY);
		employee = employeeRepository.save(employee);
		EmployeeHourlyRate employeeHourlyRate = new EmployeeHourlyRate();
		employeeHourlyRate.setEmployee(employee);
		employeeHourlyRate.setRate(HOURLY_RATE_15);
		employeeHourlyRateRepository.save(employeeHourlyRate);
		EmployeeTimeSheet timeSheet = new EmployeeTimeSheet();
		timeSheet.setEmployee(employee);
		timeSheet.setHours(HOURS_168);
		employeeTimeSheetRepository.save(timeSheet);
		return employee;
	}

	private Employee saveProvisionSettledJanuszKowalski() {
		Employee employee = new Employee();
		employee.setName(NAME_JANUSZ);
		employee.setSurname(SURNAME_KOWALSKI);
		employee.setSalaryCalculationType(SalaryCalculationType.PERCENTAGE);
		employee = employeeRepository.save(employee);
		EmployeeProvisionValue employeeProvisionValue = new EmployeeProvisionValue();
		employeeProvisionValue.setEmployee(employee);
		employeeProvisionValue.setProvision(PROVISION_20);
		employeeProvisionValueRepository.save(employeeProvisionValue);
		EmployeeSellingValue employeeSellingValue = new EmployeeSellingValue();
		employeeSellingValue.setEmployee(employee);
		employeeSellingValue.setSellingValue(SELLING_VALUE_20_000);
		employeeSellingValueRepository.save(employeeSellingValue);
		return employee;
	}


}
