package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

import java.time.LocalDate;

public class crossAdjustmentDeleteDTO {
    private String repoAdjustmentId;
    private int repoAdjustmentReversalStatus;
    private String repoAdjustmentTransfer;
    private String crossAdjustmentId;
    private int crossAdjustmentReversalStatus;
    private LocalDate crossAdjustmentDate;
    private String accountBalanceAdjustmentId;
    private int accountBalanceAdjustmentReversalStatus;
    private String accountBalanceAdjustmentTransfer;

    public crossAdjustmentDeleteDTO() {
    }

    public crossAdjustmentDeleteDTO(String repoAdjustmentId, int repoAdjustmentReversalStatus, String repoAdjustmentTransfer, String crossAdjustmentId, int crossAdjustmentReversalStatus, LocalDate crossAdjustmentDate, String accountBalanceAdjustmentId, int accountBalanceAdjustmentReversalStatus, String accountBalanceAdjustmentTransfer) {
        this.repoAdjustmentId = repoAdjustmentId;
        this.repoAdjustmentReversalStatus = repoAdjustmentReversalStatus;
        this.repoAdjustmentTransfer = repoAdjustmentTransfer;
        this.crossAdjustmentId = crossAdjustmentId;
        this.crossAdjustmentReversalStatus = crossAdjustmentReversalStatus;
        this.crossAdjustmentDate = crossAdjustmentDate;
        this.accountBalanceAdjustmentId = accountBalanceAdjustmentId;
        this.accountBalanceAdjustmentReversalStatus = accountBalanceAdjustmentReversalStatus;
        this.accountBalanceAdjustmentTransfer = accountBalanceAdjustmentTransfer;
    }

    public String getRepoAdjustmentId() {
        return repoAdjustmentId;
    }

    public void setRepoAdjustmentId(String repoAdjustmentId) {
        this.repoAdjustmentId = repoAdjustmentId;
    }

    public int getRepoAdjustmentReversalStatus() {
        return repoAdjustmentReversalStatus;
    }

    public void setRepoAdjustmentReversalStatus(int repoAdjustmentReversalStatus) {
        this.repoAdjustmentReversalStatus = repoAdjustmentReversalStatus;
    }

    public String getRepoAdjustmentTransfer() {
        return repoAdjustmentTransfer;
    }

    public void setRepoAdjustmentTransfer(String repoAdjustmentTransfer) {
        this.repoAdjustmentTransfer = repoAdjustmentTransfer;
    }

    public String getCrossAdjustmentId() {
        return crossAdjustmentId;
    }

    public void setCrossAdjustmentId(String crossAdjustmentId) {
        this.crossAdjustmentId = crossAdjustmentId;
    }

    public int getCrossAdjustmentReversalStatus() {
        return crossAdjustmentReversalStatus;
    }

    public void setCrossAdjustmentReversalStatus(int crossAdjustmentReversalStatus) {
        this.crossAdjustmentReversalStatus = crossAdjustmentReversalStatus;
    }

    public LocalDate getCrossAdjustmentDate() {
        return crossAdjustmentDate;
    }

    public void setCrossAdjustmentDate(LocalDate crossAdjustmentDate) {
        this.crossAdjustmentDate = crossAdjustmentDate;
    }

    public String getAccountBalanceAdjustmentId() {
        return accountBalanceAdjustmentId;
    }

    public void setAccountBalanceAdjustmentId(String accountBalanceAdjustmentId) {
        this.accountBalanceAdjustmentId = accountBalanceAdjustmentId;
    }

    public int getAccountBalanceAdjustmentReversalStatus() {
        return accountBalanceAdjustmentReversalStatus;
    }

    public void setAccountBalanceAdjustmentReversalStatus(int accountBalanceAdjustmentReversalStatus) {
        this.accountBalanceAdjustmentReversalStatus = accountBalanceAdjustmentReversalStatus;
    }

    public String getAccountBalanceAdjustmentTransfer() {
        return accountBalanceAdjustmentTransfer;
    }

    public void setAccountBalanceAdjustmentTransfer(String accountBalanceAdjustmentTransfer) {
        this.accountBalanceAdjustmentTransfer = accountBalanceAdjustmentTransfer;
    }
}
