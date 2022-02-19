package co.sofka.springboot.JPA.Hibernate.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.sofka.springboot.JPA.Hibernate.model.Employee;
import co.sofka.springboot.JPA.Hibernate.repository.IEmployeeJpaRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    IEmployeeJpaRepository repoEmployee;

    /**
     * Método para crear empleados.
     * 
     * @param employee datos del empleado nuevo, toma los datos del POST
     *                 requestBody
     * @return HTTP Response CREATED en caso de éxito, EXPECTATION_FAILED en
     *         caso de error.
     * @author Mathías Collazo
     **/
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            Employee _employee = repoEmployee.save(new Employee(employee.getFirstName(), employee.getLastName(), employee.getEmployeeid(), employee.getRole()));
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener una lista con todos los empleados.
     * 
     * @usageMethod localhost:8080/api/employees with GET
     * @return HTTP Response OK con la lista de empleados en caso de éxito,
     *         NO_CONTENT en caso de éxito pero sin empleados, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = new ArrayList<Employee>();
            repoEmployee.findAll().forEach(employees::add);
            if (!employees.isEmpty()) {
                return new ResponseEntity<>(employees, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    
}
