package internetshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", columnDefinition = "INT")
    private Long roleId;
    @Enumerated(EnumType.STRING)
    @Column(name = "name", columnDefinition = "VARCHAR")
    private RoleName roleName;

    public Role() {
    }

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
