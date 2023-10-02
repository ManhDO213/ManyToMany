package spring.tutorial.manytomany.service;


import spring.tutorial.manytomany.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public Employee addEmployee(Map<String, Object> request) ;
    public Employee editEmployee(long id, Map<String, Object> request);
    public Boolean deleteEmployee(long id);
    public List<Employee> getAllEmployee();
    public Employee findById(long id);
}
