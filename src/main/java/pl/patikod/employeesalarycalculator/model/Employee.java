package pl.patikod.employeesalarycalculator.model;

import lombok.Data;
import pl.patikod.employeesalarycalculator.model.salarycalculation.SalaryCalculator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    @OneToOne
    private SalaryCalculator salaryCalculator;

    public BigDecimal calculateSalary() {
        return salaryCalculator.calculateSalary();
    }

}

