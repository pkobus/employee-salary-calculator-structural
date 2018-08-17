package pl.patikod.employeesalarycalculator.model.salarycalculation;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.patikod.employeesalarycalculator.model.EmployeeTimeSheet;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class HourlyRateSalaryCalculator extends SalaryCalculator {

    private BigDecimal rate;

    @OneToOne
    private EmployeeTimeSheet employeeTimeSheet;

    HourlyRateSalaryCalculator() {
    }

    public HourlyRateSalaryCalculator(BigDecimal rate, EmployeeTimeSheet employeeTimeSheet) {
        this.rate = rate;
        this.employeeTimeSheet = employeeTimeSheet;
    }

    @Override
    public BigDecimal calculateSalary() {
        BigDecimal hours = new BigDecimal(employeeTimeSheet.getHours());
        return rate.multiply(hours);
    }
}
