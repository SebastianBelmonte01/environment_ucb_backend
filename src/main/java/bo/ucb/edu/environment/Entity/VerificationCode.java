package bo.ucb.edu.environment.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "verification_code")
public class VerificationCode {
    @Id
    @Column(name = "vc_uuid")
    private String vcUuid;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "code")
    private Integer code;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "version")
    private Integer version;

    @Column(name = "tx_date")
    private Date txDate;

    @Column(name = "tx_host")
    private String txHost;

    @Column(name = "tx_user")
    private String txUser;

    // Constructors, getters, and setters

    public VerificationCode() {
        // Default constructor
    }

    public VerificationCode(String vcUuid, Integer userId, Integer code, Boolean status, Integer version, Date txDate, String txHost, String txUser) {
        this.vcUuid = vcUuid;
        this.userId = userId;
        this.code = code;
        this.status = status;
        this.version = version;
        this.txDate = txDate;
        this.txHost = txHost;
        this.txUser = txUser;
    }

    // Getters and setters

    public String getVcUuid() {
        return vcUuid;
    }

    public void setVcUuid(String vcUuid) {
        this.vcUuid = vcUuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
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
}
