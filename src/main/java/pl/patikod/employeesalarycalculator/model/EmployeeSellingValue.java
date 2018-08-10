package pl.patikod.employeesalarycalculator.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@Entity
public class EmployeeSellingValue {

    @Id
    @GeneratedValue()
    private Long id;

    @OneToOne
    private Employee employee;

    private BigDecimal sellingValue;
}
