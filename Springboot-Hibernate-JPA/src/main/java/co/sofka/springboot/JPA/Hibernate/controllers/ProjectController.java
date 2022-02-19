package co.sofka.springboot.JPA.Hibernate.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.sofka.springboot.JPA.Hibernate.model.Project;
import co.sofka.springboot.JPA.Hibernate.repository.IProjectJpaRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    IProjectJpaRepository repoProjects;

    /**
     * Método para crear empleados.
     * 
     * @param project datos del proyecto nuevo, toma los datos del POST
     *                requestBody
     * @return HTTP Response CREATED en caso de éxito, EXPECTATION_FAILED en
     *         caso de error.
     * @author Mathías Collazo
     **/
    @PostMapping("/projects")
    public ResponseEntity<Project> createEmployee(@RequestBody Project project) {
        try {
            Project _project = repoProjects.save(new Project(project.getName()));
            return new ResponseEntity<>(_project, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener una lista con todos los proyectos.
     * 
     * @usageMethod localhost:8080/api/projects with GET
     * @return HTTP Response OK con la lista de proyectos en caso de éxito,
     *         NO_CONTENT en caso de éxito pero sin proyectos, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        try {
            List<Project> projects = new ArrayList<Project>();
            repoProjects.findAll().forEach(projects::add);
            if (!projects.isEmpty()) {
                return new ResponseEntity<>(projects, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener un proyecto mediante su id
     * 
     * @UsageMethod localhost:8080/api/projects?id={id} with GET
     * @param id id del proyecto del cuál desea obtener su información
     * @return HTTP Response OK con el proyecto en caso de éxito, NO_CONTENT en
     *         caso de éxito pero sin proyecto, EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @GetMapping(value = "/projects", params = "id")
    public ResponseEntity<Project> getProjectById(@RequestParam(value = "id") Long id) {
        try {
            Optional<Project> project = repoProjects.findById(id);
            if (project.isPresent()) {
                return new ResponseEntity<>(project.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener un proyecto mediante su name
     * 
     * @UsageMethod localhost:8080/api/projects?name={name} with GET
     * @param name nombre del proyecto del cuál desea obtener su información
     * @return HTTP Response OK con el proyecto en caso de éxito, NO_CONTENT en
     *         caso de éxito pero sin proyecto, EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @GetMapping(value = "/projects", params = "name")
    public ResponseEntity<List<Project>> getProjectByName(@RequestParam(value = "name") String name) {
        List<Project> project = repoProjects.findByName(name);
        try {
            if (!project.isEmpty()) {
                return new ResponseEntity<>(project, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para actualizar un proyecto mediante su id
     * 
     * @UsageMethod localhost:8080/api/projects/{id} with PUT
     * @param id      id del proyecto del cuál desea actualizar su información
     * @param project datos para modificar el proyecto, toma los datos del POST
     *                requestBody
     * @return HTTP Response OK en caso de éxito, NO_CONTENT en
     *         caso de éxito pero sin encontrar el proyecto, y EXPECTATION_FAILED en
     *         caso de error.
     * @author Mathías Collazo
     **/
    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable("id") Long id,
            @RequestBody Project project) {
        Optional<Project> projectData = repoProjects.findById(id);
        try {
            if (projectData.isPresent()) {
                Project _project = projectData.get();
                if (project.getName() != null)
                    _project.setName(project.getName());
                return new ResponseEntity<>(repoProjects.save(_project), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para eliminar todos los proyectos.
     * 
     * @UsageMethod localhost:8080/api/projects with DELETE
     * @return HTTP Response OK en caso de éxito, y EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @DeleteMapping("/projects")
    public ResponseEntity<HttpStatus> deleteAllProjects() {
        try {
            repoProjects.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para eliminar un proyecto por su id.
     * 
     * @UsageMethod localhost:8080/api/projects/{id} with DELETE
     * @return HTTP Response OK en caso de éxito, y EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable("id") Long id) {
        try {
            repoProjects.deleteById(id);
            return new ResponseEntity<>("Project deleted succesfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}