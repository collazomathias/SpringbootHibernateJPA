# Springboot with Hibernate and JPA
### Funcionalidad del proyecto: 
|Metodo HTTP|GET|POST|DELETE|PUT|
|-----------|---|----|------|---|
|/employees|Listar los empleados|Crear nuevo empleado|Borrar todos los empleados|Error|
|/employees/{token}|Error|Error|Elimina el empleado con id {token}|Actualiza el empleado con id {token}|
|/employees?{param}={token}|Listar empleado con {param} {token}|Error|Error|Error|
|/role|Listar los role|Crear nuevo role|Borrar todos los role|Error|
|/role/{token}|Error|Error|Elimina el role con id {token}|Actualiza el role con id {token}|
|/role?{param}={token}|Listar role con {param} {token}|Error|Error|Error|
|/projects|Listar los proyectos|Crear nuevo proyectos|Borrar todos los proyectos|Error|
|/projects/{token}|Error|Error|Elimina el proyectos con id {token}|Actualiza el proyectos con id {token}|
|/projects?{param}={token}|Listar proyectos con {param} {token}|Error|Error|Error|

### Método para crear empleados/roles/proyectos:
![Alt Text](https://imgur.com/cCSRq6v.gif)

### Método para mostrar todos los empleados/roles/proyectos:
![Alt Text](https://imgur.com/kKv1f5j.gif)

### Método para mostrar empleados/roles/proyectos por parámetro:
![Alt Text](https://imgur.com/b5HE8X2.gif)

### Método para actualizar empleados/roles/proyectos por id:
![Alt Text](https://imgur.com/sgt2QwW.gif)

### Método para eliminar todos los empleados/roles/proyectos:
![Alt Text](https://imgur.com/s1Us1h5.gif)

### Método para eliminar empleados/roles/proyectos por id:
![Alt Text](https://imgur.com/6wXgt2h.gif)