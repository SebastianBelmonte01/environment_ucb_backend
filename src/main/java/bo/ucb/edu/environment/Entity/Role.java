package bo.ucb.edu.environment.Entity;
import java.util.List;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SR_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_host", nullable = false)
    private String txHost;

    @Column(name = "tx_user", nullable = false)
    private String txUser;

    @Column(name = "tx_date", nullable = false)
    private Date txDate;

    @OneToMany(mappedBy = "srRole", cascade = CascadeType.ALL)
    private List<RoleGroup> roleGroups;

    public Role() {
    }

    public Role(Long roleId, String name, boolean status, String txHost, String txUser, Date txDate, List<RoleGroup> roleGroups) {
        this.roleId = roleId;
        this.name = name;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.roleGroups = roleGroups;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<RoleGroup> getRoleGroups() {
        return roleGroups;
    }

    public void setRoleGroups(List<RoleGroup> roleGroups) {
        this.roleGroups = roleGroups;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                ", roleGroups=" + roleGroups +
                '}';
    }
}
