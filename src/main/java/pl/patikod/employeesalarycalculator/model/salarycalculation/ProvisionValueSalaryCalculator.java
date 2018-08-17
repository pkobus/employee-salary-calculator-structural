package pl.patikod.employeesalarycalculator.model.salarycalculation;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.patikod.employeesalarycalculator.model.EmployeeSellingValue;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class ProvisionValueSalaryCalculator extends SalaryCalculator {

    private BigDecimal provisionValue;

    @OneToOne
    private EmployeeSellingValue employeeSellingValue;

    ProvisionValueSalaryCalculator() {
    }

    public ProvisionValueSalaryCalculator(BigDecimal provisionValue, EmployeeSellingValue employeeSellingValue) {
        this.provisionValue = provisionValue;
        this.employeeSellingValue = employeeSellingValue;
    }

    @Override
    public BigDecimal calculateSalary() {
        return employeeSellingValue.getValue().multiply(provisionValue);
    }
}
