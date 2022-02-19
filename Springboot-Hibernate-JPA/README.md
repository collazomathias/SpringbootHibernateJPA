# springboot-jpa-hibernate

Esto es una aplicación de ejemplo que provee una API REST de empleados, proyectos y roles

La API es un proyecto creado en Java utilizando Springboot como framework + JPA + Hibernate

## Ejecutar la app

    mvnw.cmd spring-boot:run

# API REST

La API REST de la aplicación se explica a continuación


| METODO HTTP               | POST                  | GET                                   | PUT                                     | DELETE                        |
| ------------------------- | --------------------- | ------------------------------------- | --------------------------------------- | ----------------------------- |
| CRUD OP                   | CREATE                | READ                                  | UPDATE                                  | DELETE                        |
| /employees                | Crear nuevo empleado  | Listar empleados                      | Error                                   | Eliminar todos los empleados  |
| /employees/1              | Error                 | Mostrar empleado                      | Modificar empleado. Si no existe error  | Eliminar empleado             |
| /employees?employeeid=a32 | Error                 | Mostrar empleado por su identificador | Error                                   | Error                         |
| /employees?lastName=abc   | Error                 | Listar empleados por su apellido      | Error                                   | Error                         |
| .                         |                       |                                       |                                         |                               |
| /projects                 | Crear nuevo proyecto  | Listar proyectos                      | Error                                   | Eliminar todos los proyectos  |
| /projects/1               | Error                 | Mostrar proyecto                      | Modificar proyecto. Si no existe error  | Eliminar proyecto             |
| /projects?name=abc        | Error                 | Mostrar proyecto por su nombre        | Error                                   | Error                         |
| .                         |                       |                                       |                                         |                               |
| /roles                    | Crear nuevo rol       | Listar roles                          | Error                                   | Eliminar todos los roles      |
| /roles/1                  | Error                 | Mostrar rol                           | Modificar rol. Si no existe error       | Eliminar rol                  |
| /roles?name=abc           | Error                 | Mostrar rol por su nombre             | Error                                   | Error                         |