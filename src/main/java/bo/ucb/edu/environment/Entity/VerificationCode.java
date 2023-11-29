package bo.ucb.edu.environment.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "verification_code")
public class VerificationCode {
    @Id
    @Column(name = "vcuuid")
    private String vcUuid;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "code")
    private Long code;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "version")
    private Integer version;

    @Column(name = "txdate")
    private Date txDate;

    @Column(name = "txhost")
    private String txHost;

    @Column(name = "txuser")
    private String txUser;

    // Constructors, getters, and setters

    public VerificationCode() {
        // Default constructor
    }

    public VerificationCode(String vcUuid, Long userId, Long code, Boolean status, Integer version, Date txDate, String txHost, String txUser) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
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

    @Override
    public String toString() {
        return "VerificationCode{" +
                "vcUuid='" + vcUuid + '\'' +
                ", userId=" + userId +
                ", code=" + code +
                ", status=" + status +
                ", version=" + version +
                ", txDate=" + txDate +
                ", txHost='" + txHost + '\'' +
                ", txUser='" + txUser + '\'' +
                '}';
    }
}
