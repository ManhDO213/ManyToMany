package spring.tutorial.manytomany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import spring.tutorial.manytomany.model.Employee;
import spring.tutorial.manytomany.model.Role;
import spring.tutorial.manytomany.repository.EmployeeRepository;

import java.util.*;

@Service
public class EmployeeImpService implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleImpService roleImpService;

    @Override
    public Employee addEmployee(@RequestBody Map<String, Object> request) {

        Employee employee = new Employee();
        Set<Role> roles = new HashSet<>();
        List<Long> roleIds = (List<Long>) request.get("roleID");
        employee.setEmail(request.get("email").toString());
        employee.setName(request.get("name").toString());
        if(roleIds != null){
            for (int i = 0 ;i< roleIds.size();i++){
                Role role = roleImpService.findById(Long.parseLong(roleIds.get(i)+""));
                roles.add(role);
            }
        }

        employee.setRoles(roles);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee editEmployee(@PathVariable long id, Map<String, Object> request) {

        List<Long> roleIds = (List<Long>) request.get("roleID");
        Set<Role> roles = new HashSet<>();
        Optional<Employee> optionalPerson =  Optional.ofNullable(findById(id));
        if(optionalPerson.isPresent()){
            Employee employee = optionalPerson.get();
            employee.setName(request.get("name").toString());
            employee.setEmail(request.get("email").toString());

            if (roleIds != null) {
                for (int i = 0; i < roleIds.size(); i++) {
                    Role role = roleImpService.findById(Long.parseLong(roleIds.get(i) + ""));
                    roles.add(role);
                }
            }
            employee.setRoles(roles);
            return employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public Boolean deleteEmployee(long id) {
        if (id>=1){
            Employee employee =  employeeRepository.getById(id);
            if(employee != null){
                employeeRepository.delete(employee);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findById(id).get();
    }
}
