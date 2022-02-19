package co.sofka.springboot.JPA.Hibernate.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * Método para obtener un empleado mediante su id
     * 
     * @UsageMethod localhost:8080/api/employees?id={id} with GET
     * @param id id del empleado del cuál desea obtener su información
     * @return HTTP Response OK con el empleado en caso de éxito, NO_CONTENT en
     *         caso de éxito pero sin empleado, EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @GetMapping(value = "/employees", params = "id")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam(value = "id") Long id) {
        try {
            Optional<Employee> employee = repoEmployee.findById(id);
            if (employee.isPresent()) {
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener un empleado mediante su employeeId
     * 
     * @UsageMethod localhost:8080/api/employees?employeeId={employeeId} with GET
     * @param employeeId id del empleado del cuál desea obtener su información
     * @return HTTP Response OK con el empleado en caso de éxito, NO_CONTENT en
     *         caso de éxito pero sin empleado, EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @GetMapping(value = "/employees", params = "employeeId")
    public ResponseEntity<Employee> getEmployeeByEmployeeId(@RequestParam(value = "employeeId") String employeeId) {
        try {
            Optional<Employee> employee = repoEmployee.findByEmployeeid(employeeId);
            if (employee.isPresent()) {
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener un empleado mediante su firstName
     * 
     * @UsageMethod localhost:8080/api/employees?firstName={firstName} with GET
     * @param firstName primer nombre del empleado del cuál desea obtener su
     *                  información
     * @return HTTP Response OK con el empleado en caso de éxito, NO_CONTENT en
     *         caso de éxito pero sin empleado, EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @GetMapping(value = "/employees", params = "firstName")
    public ResponseEntity<List<Employee>> getEmployeeByFirstName(@RequestParam(value = "firstName") String firstName) {
        List<Employee> employee = repoEmployee.findByFirstName(firstName);
        try {
            if (!employee.isEmpty()) {
                return new ResponseEntity<>(employee, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener un empleado mediante su lastName
     * 
     * @UsageMethod localhost:8080/api/employees?lastName={lastName} with GET
     * @param lastName Primer apellido del empleado del cuál desea obtener su
     *                 información
     * @return HTTP Response OK con el empleado en caso de éxito, NO_CONTENT en
     *         caso de éxito pero sin empleado, EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @GetMapping(value = "/employees", params = "lastName")
    public ResponseEntity<List<Employee>> getEmployeeByLastName(@RequestParam(value = "lastName") String lastName) {
        List<Employee> employee = repoEmployee.findByLastName(lastName);
        try {
            if (!employee.isEmpty()) {
                return new ResponseEntity<>(employee, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para actualizar un empleado mediante su employeeId
     * 
     * @UsageMethod localhost:8080/api/employees/{employeeId} with PUT
     * @param employeeId employeeEd del empleado del cuál desea actualizar su
     *                   información
     * @param employee   datos para modificar el empleado, toma los datos del POST
     *                   requestBody
     * @return HTTP Response OK en caso de éxito, NO_CONTENT en
     *         caso de éxito pero sin encontrar el empleado, y EXPECTATION_FAILED en
     *         caso de error.
     * @author Mathías Collazo
     **/
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployeeByEmployeeId(@PathVariable("employeeId") String employeeId,
            @RequestBody Employee employee) {
        Optional<Employee> employeeData = repoEmployee.findByEmployeeid(employeeId);
        try {
            if (employeeData.isPresent()) {
                Employee _employee = employeeData.get();
                if (employee.getFirstName() != null)
                    _employee.setFirstName(employee.getFirstName());
                if (employee.getLastName() != null)
                    _employee.setLastName(employee.getLastName());
                if (employee.getEmployeeid() != null)
                    _employee.setEmployeeid(employee.getEmployeeid());
                if (employee.getRole() != null)
                    _employee.setRole(employee.getRole());
                if (employee.getProjects() != null)
                    _employee.setProjects(employee.getProjects());
                return new ResponseEntity<>(repoEmployee.save(_employee), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para eliminar todos los empleados.
     * 
     * @UsageMethod localhost:8080/api/employees with DELETE
     * @return HTTP Response OK en caso de éxito, y EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            repoEmployee.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para eliminar un empleado por su employeeId.
     * 
     * @UsageMethod localhost:8080/api/employees/{employeeId} with DELETE
     * @return HTTP Response OK en caso de éxito, y EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployeeByEmployeeId(@PathVariable("employeeId") String employeeId) {
        try {
            Optional<Employee> employee = repoEmployee.findByEmployeeid(employeeId);
            repoEmployee.deleteById(employee.get().getId());
            return new ResponseEntity<>("Employee deleted succesfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    
}
