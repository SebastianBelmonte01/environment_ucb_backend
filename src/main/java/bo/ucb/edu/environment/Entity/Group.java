package bo.ucb.edu.environment.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SR_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_host", nullable = false)
    private String txHost;

    @Column(name = "tx_user", nullable = false)
    private String txUser;

    @Column(name = "tx_date", nullable = false)
    private Date txDate;

    @OneToMany(mappedBy = "srGroup", cascade = CascadeType.ALL)
    private List<UserGroup> userGroups;

    @OneToMany(mappedBy = "srGroup", cascade = CascadeType.ALL)
    private List<RoleGroup> roleGroups;

    // Getters and setters

public Group() {
    }

    public Group(Long groupId, String name, String description, boolean status, String txHost, String txUser, Date txDate, List<UserGroup> userGroups, List<RoleGroup> roleGroups) {
        this.groupId = groupId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.userGroups = userGroups;
        this.roleGroups = roleGroups;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public List<RoleGroup> getRoleGroups() {
        return roleGroups;
    }

    public void setRoleGroups(List<RoleGroup> roleGroups) {
        this.roleGroups = roleGroups;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                ", userGroups=" + userGroups +
                ", roleGroups=" + roleGroups +
                '}';
    }
}