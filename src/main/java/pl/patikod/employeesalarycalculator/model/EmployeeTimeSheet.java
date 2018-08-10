package pl.patikod.employeesalarycalculator.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class EmployeeTimeSheet {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Employee employee;

    private Integer hours;
}
