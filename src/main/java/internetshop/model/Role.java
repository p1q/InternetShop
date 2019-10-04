package internetshop.model;

public class Role {
    private Long roleId;
    private RoleName roleName;

    public Role(Long roleId, String roleName) {
        this.roleId = roleId;
        try {
            this.roleName = RoleName.valueOf(roleName);
        } catch (IllegalArgumentException e) {
            this.roleName = RoleName.USER;
        }
    }

    public Long getRoleId() {
        return roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public enum RoleName {
        USER, ADMIN, MANAGER
    }
}
