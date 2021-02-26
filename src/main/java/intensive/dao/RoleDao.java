package intensive.dao;

import intensive.model.Role;

public interface RoleDao {
    void add(Role role);

    Role getRoleByName(String roleName);
}
