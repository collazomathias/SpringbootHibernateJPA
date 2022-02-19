package co.sofka.springboot.JPA.Hibernate.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.sofka.springboot.JPA.Hibernate.model.Role;
import co.sofka.springboot.JPA.Hibernate.repository.IRoleJpaRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    IRoleJpaRepository repoRoles;

    /**
     * Método para crear roles.
     * 
     * @param role datos del role nuevo, toma los datos del POST requestBody
     * @return HTTP Response CREATED en caso de éxito, EXPECTATION_FAILED en
     *         caso de error.
     * @author Mathías Collazo
     **/
    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            Role _role = repoRoles.save(new Role(role.getName()));
            return new ResponseEntity<>(_role, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener una lista con todos los roles.
     * 
     * @usageMethod localhost:8080/api/roles with GET
     * @return HTTP Response OK con la lista de roles en caso de éxito,
     *         NO_CONTENT en caso de éxito pero sin roles, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            List<Role> roles = new ArrayList<Role>();
            repoRoles.findAll().forEach(roles::add);
            if (!roles.isEmpty()) {
                return new ResponseEntity<>(roles, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    
}