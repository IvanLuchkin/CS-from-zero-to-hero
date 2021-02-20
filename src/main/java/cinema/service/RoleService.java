package cinema.service;

import cinema.model.Role;

public interface RoleService {
    void addRole(Role role);

    Role getRoleByName(String roleName);
}
