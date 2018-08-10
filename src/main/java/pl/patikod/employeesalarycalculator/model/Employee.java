package pl.patikod.employeesalarycalculator.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    private SalaryCalculationType salaryCalculationType;

}

