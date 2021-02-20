package cinema.controller;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InjectController {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public InjectController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void addRoles() {
        Role adminRole = new Role(Role.RoleName.ADMIN);
        Role userRole = new Role(Role.RoleName.USER);
        roleService.addRole(adminRole);
        roleService.addRole(userRole);
        User admin = new User();
        admin.setEmail("admin");
        admin.setRoles(List.of(adminRole, userRole));
        admin.setPassword("admin");
        userService.add(admin);
    }
}
