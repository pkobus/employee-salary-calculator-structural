package pl.patikod.employeesalarycalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patikod.employeesalarycalculator.model.EmployeeSellingValue;

@Repository
public interface EmployeeSellingValueRepository extends JpaRepository<EmployeeSellingValue, Long> {

}
