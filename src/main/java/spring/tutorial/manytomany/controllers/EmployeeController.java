package spring.tutorial.manytomany.controllers;

import lombok.RequiredArgsConstructor;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import spring.tutorial.manytomany.model.Employee;
import spring.tutorial.manytomany.model.Role;
import spring.tutorial.manytomany.service.EmployeeImpService;
import spring.tutorial.manytomany.service.RoleImpService;

import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeImpService employeeImpService;
    Logger logger = Logger.getLogger(this.getClass().getName());
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employee = employeeImpService.getAllEmployee();
        return ResponseEntity.ok(employee);
    }
    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Employee> addEmployee(@RequestBody Map<String, Object> request){
        Employee employee = employeeImpService.addEmployee(request);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> editResponseEntity(@PathVariable long id, @RequestBody Map<String, Object> request){
        Employee employee = employeeImpService.editEmployee(id,request);
        return ResponseEntity.ok(employee);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResponseEntity(@PathVariable long id) {
        boolean deleted = employeeImpService.deleteEmployee(id);
        if (deleted) {
            logger.info("data Adress =====> Delete succesfully");
            return ResponseEntity.ok("Adress deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
