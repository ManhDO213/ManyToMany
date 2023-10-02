package spring.tutorial.manytomany.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import spring.tutorial.manytomany.model.Employee;
import spring.tutorial.manytomany.model.Role;
import spring.tutorial.manytomany.repository.EmployeeRepository;
import spring.tutorial.manytomany.repository.RoleRepository;

import java.util.List;
import java.util.Map;

@Service
public class RoleImpService implements RoleService{

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Role addPerson(Role role) {
        if(role != null){
        return roleRepository.save(role);
        }
        return null;
    }

    @Override
    public Role editRole(long id, Role role) {
        if(role != null){
            Role role1 = roleRepository.getById(id);
            if(role1 != null){
                role1.setName(role.getName());
                role1.setEmployees(role.getEmployees());
                return roleRepository.save(role1);
            }
        }
        return null;
    }

    @Transactional
    @Override
    public Boolean deleteRole(@PathVariable long id, @RequestBody Map<String, Object> request) {
        if (id>=1){
            Long employeeID = Long.parseLong(request.get("employeeID").toString());
            Employee employee =  employeeRepository.findById(employeeID).orElse(null);
            Role role =  roleRepository.findById(id).orElse(null);
            if(role != null){
                employee.getRoles().remove(role);
                roleRepository.delete(role);
                employeeRepository.save(employee);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }


    @Override
    public Role findById(long id) {
        return roleRepository.findById(id).get();
    }



}
