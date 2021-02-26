package intensive.service;

import intensive.model.Role;

public interface RoleService {
    void addRole(Role role);

    Role getRoleByName(String roleName);
}
