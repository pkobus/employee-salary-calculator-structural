package pl.patikod.employeesalarycalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patikod.employeesalarycalculator.model.EmployeeProvisionValue;

@Repository
public interface EmployeeProvisionValueRepository extends JpaRepository<EmployeeProvisionValue, Long> {

    EmployeeProvisionValue findEmployeeProvisionValueByEmployeeId(Long employeeId);

}
