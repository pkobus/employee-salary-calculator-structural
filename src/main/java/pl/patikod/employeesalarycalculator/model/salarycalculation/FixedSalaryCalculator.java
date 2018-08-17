package pl.patikod.employeesalarycalculator.model.salarycalculation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import java.math.BigDecimal;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class FixedSalaryCalculator extends SalaryCalculator {

    private BigDecimal salary;

    FixedSalaryCalculator() {
    }

    public FixedSalaryCalculator(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public BigDecimal calculateSalary() {
        return salary;
    }

}
