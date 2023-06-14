package bo.ucb.edu.environment.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "SR_role_group")
public class RoleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_group_id")
    private Long roleGroupId;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_host", nullable = false)
    private String txHost;

    @Column(name = "tx_user", nullable = false)
    private String txUser;

    @Column(name = "tx_date", nullable = false)
    private Date txDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SR_role_role_id", nullable = false)
    private Role srRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SR_group_group_id", nullable = false)
    private Group srGroup;

    // Getters and setters

public RoleGroup() {
    }

    public RoleGroup(Long roleGroupId, boolean status, String txHost, String txUser, Date txDate, Role srRole, Group srGroup) {
        this.roleGroupId = roleGroupId;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.srRole = srRole;
        this.srGroup = srGroup;
    }

    public Long getRoleGroupId() {
        return roleGroupId;
    }

    public void setRoleGroupId(Long roleGroupId) {
        this.roleGroupId = roleGroupId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Role getSrRole() {
        return srRole;
    }

    public void setSrRole(Role srRole) {
        this.srRole = srRole;
    }

    public Group getSrGroup() {
        return srGroup;
    }

    public void setSrGroup(Group srGroup) {
        this.srGroup = srGroup;
    }

    @Override
    public String toString() {
        return "RoleGroup{" +
                "roleGroupId=" + roleGroupId +
                ", status=" + status +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                ", srRole=" + srRole +
                ", srGroup=" + srGroup +
                '}';
    }
}
