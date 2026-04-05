package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank")
public class bank {
    @Id
    @Column(name = "bank_id", length = 7, nullable = false)
    private String bankId;
    @Column(name = "bank_name", length = 100, nullable = false)
    private String bankName;

    public bank() {
    }

    public bank(String bankId, String bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
