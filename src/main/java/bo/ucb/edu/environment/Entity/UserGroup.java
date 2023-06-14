package bo.ucb.edu.environment.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "SR_user_group")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_id")
    private Long userGroupId;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_host", nullable = false)
    private String txHost;

    @Column(name = "tx_user", nullable = false)
    private String txUser;

    @Column(name = "tx_date", nullable = false)
    private Date txDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SR_user_user_id", nullable = false)
    private User srUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SR_group_group_id", nullable = false)
    private Group srGroup;

// Getters and setters

    public UserGroup() {
    }

    public UserGroup(Long userGroupId, boolean status, String txHost, String txUser, Date txDate, User srUser, Group srGroup) {
        this.userGroupId = userGroupId;
        this.status = status;
        this.txHost = txHost;
        this.txUser = txUser;
        this.txDate = txDate;
        this.srUser = srUser;
        this.srGroup = srGroup;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
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

    public User getSrUser() {
        return srUser;
    }

    public void setSrUser(User srUser) {
        this.srUser = srUser;
    }

    public Group getSrGroup() {
        return srGroup;
    }

    public void setSrGroup(Group srGroup) {
        this.srGroup = srGroup;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "userGroupId=" + userGroupId +
                ", status=" + status +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                ", txDate=" + txDate +
                ", srUser=" + srUser +
                ", srGroup=" + srGroup +
                '}';
    }
}