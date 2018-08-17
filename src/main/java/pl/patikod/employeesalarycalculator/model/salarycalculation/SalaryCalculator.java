package pl.patikod.employeesalarycalculator.model.salarycalculation;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.math.BigDecimal;

@Data
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class SalaryCalculator {

    @Id
    @GeneratedValue
    private Long id;

    public abstract BigDecimal calculateSalary();

}
