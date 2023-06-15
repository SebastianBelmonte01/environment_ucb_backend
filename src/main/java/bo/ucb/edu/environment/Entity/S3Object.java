package bo.ucb.edu.environment.Entity;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "S3_object")
public class S3Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s3_object_id")
    private Long s3ObjectId;

    @ManyToOne
    @JoinColumn(name = "SR_claim_claim_id", referencedColumnName = "claim_id", nullable = false)
    private Claim claim;

    @Column(name = "filename", nullable = false, length = 100)
    private String filename;

    @Column(name = "URL", nullable = false, length = 100)
    private String url;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "tx_user", nullable = false, length = 30)
    private String transactionUser;

    @Column(name = "tx_date", nullable = false)
    private Date transactionDate;

    @Column(name = "tx_host", nullable = false, length = 30)
    private String transactionHost;

    public S3Object() {
    }

    public S3Object(Long s3ObjectId, Claim claim, String filename, String url, boolean status, String transactionUser, Date transactionDate, String transactionHost) {
        this.s3ObjectId = s3ObjectId;
        this.claim = claim;
        this.filename = filename;
        this.url = url;
        this.status = status;
        this.transactionUser = transactionUser;
        this.transactionDate = transactionDate;
        this.transactionHost = transactionHost;
    }

    public Long getS3ObjectId() {
        return s3ObjectId;
    }

    public void setS3ObjectId(Long s3ObjectId) {
        this.s3ObjectId = s3ObjectId;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTransactionUser() {
        return transactionUser;
    }

    public void setTransactionUser(String transactionUser) {
        this.transactionUser = transactionUser;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionHost() {
        return transactionHost;
    }

    public void setTransactionHost(String transactionHost) {
        this.transactionHost = transactionHost;
    }

    @Override
    public String toString() {
        return "S3Object{" +
                "s3ObjectId=" + s3ObjectId +
                ", claim=" + claim +
                ", filename='" + filename + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", transactionUser='" + transactionUser + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionHost='" + transactionHost + '\'' +
                '}';
    }
}
