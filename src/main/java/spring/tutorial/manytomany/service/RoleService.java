package spring.tutorial.manytomany.service;

import spring.tutorial.manytomany.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    public Role addPerson(Role role);
    public Role editRole(long id, Role role);
    public Boolean deleteRole(long id, Map<String, Object> request);
    public List<Role> getAllRole();
    public Role findById(long id);


}
