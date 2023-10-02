package spring.tutorial.manytomany.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.tutorial.manytomany.model.Employee;
import spring.tutorial.manytomany.model.Role;
import spring.tutorial.manytomany.service.EmployeeImpService;
import spring.tutorial.manytomany.service.RoleImpService;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private EmployeeImpService employeeImpService;
    @Autowired
    private RoleImpService roleImpService;

    Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllEmployee(){
        List<Role> role = roleImpService.getAllRole();
        return ResponseEntity.ok(role);
    }

    @PostMapping("/add")
    public ResponseEntity<Role> addressResponseEntity(@RequestBody Role role){
        Role role1 =roleImpService.addPerson(role);
        logger.info("new Data Adress =====> " + role1);
        return ResponseEntity.ok(role1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResponseEntity(@PathVariable(required = false) int id,@RequestBody(required = false) Map<String , Object> request) {
        boolean deletedRole = roleImpService.deleteRole(id, request);
        if (deletedRole) {
            logger.info("data Adress =====> Delete succesfully");
            return ResponseEntity.ok("Role deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
