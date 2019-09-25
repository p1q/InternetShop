package internetshop.model;

import internetshop.service.IdGenerator;

public class Role {
    private final Long roleId;
    private RoleName roleName;

    public Role() {
        this.roleId = IdGenerator.generateRoleId();
    }

    public Long getRoleId() {
        return roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    public Role(RoleName roleName) {
        this();
        this.roleName = roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public enum RoleName {
        USER, ADMIN
    }
}
