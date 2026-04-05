package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;


import org.springframework.web.multipart.MultipartFile;

public class getVoucherDetailsDTO {
    private String voucherNumber;
    private String date;
    private String preparedBy;
    private String checkedBy;
    private String approvedBy;
    private String prepareUserPosition;
    private String checkedUserPosition;
    private String approvedUserPosition;
    private byte[] preparedSignature;
    private byte[] checkedSignature;
    private byte[] approvedSignature;
    private String toAccount;
    private String toBranch;
    private String toBank;
    private int toGl;
    private float transferAmount;
    private String fromAccount;
    private String fromBank;
    private String fromBranch;
    private int fromGl;

    public getVoucherDetailsDTO() {
    }

    public getVoucherDetailsDTO(String voucherNumber, String date, String preparedBy, String checkedBy, String approvedBy, String prepareUserPosition, String checkedUserPosition, String approvedUserPosition, byte[] preparedSignature, byte[] checkedSignature, byte[] approvedSignature, String toAccount, String toBranch, String toBank, int toGl, float transferAmount, String fromAccount, String fromBank, String fromBranch, int fromGl) {
        this.voucherNumber = voucherNumber;
        this.date = date;
        this.preparedBy = preparedBy;
        this.checkedBy = checkedBy;
        this.approvedBy = approvedBy;
        this.prepareUserPosition = prepareUserPosition;
        this.checkedUserPosition = checkedUserPosition;
        this.approvedUserPosition = approvedUserPosition;
        this.preparedSignature = preparedSignature;
        this.checkedSignature = checkedSignature;
        this.approvedSignature = approvedSignature;
        this.toAccount = toAccount;
        this.toBranch = toBranch;
        this.toBank = toBank;
        this.toGl = toGl;
        this.transferAmount = transferAmount;
        this.fromAccount = fromAccount;
        this.fromBank = fromBank;
        this.fromBranch = fromBranch;
        this.fromGl = fromGl;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getToBranch() {
        return toBranch;
    }

    public void setToBranch(String toBranch) {
        this.toBranch = toBranch;
    }

    public String getToBank() {
        return toBank;
    }

    public void setToBank(String toBank) {
        this.toBank = toBank;
    }

    public int getToGl() {
        return toGl;
    }

    public void setToGl(int toGl) {
        this.toGl = toGl;
    }

    public float getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(float transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getFromBank() {
        return fromBank;
    }

    public void setFromBank(String fromBank) {
        this.fromBank = fromBank;
    }

    public String getFromBranch() {
        return fromBranch;
    }

    public void setFromBranch(String fromBranch) {
        this.fromBranch = fromBranch;
    }

    public int getFromGl() {
        return fromGl;
    }

    public void setFromGl(int fromGl) {
        this.fromGl = fromGl;
    }

    public String getPrepareUserPosition() {
        return prepareUserPosition;
    }

    public void setPrepareUserPosition(String prepareUserPosition) {
        this.prepareUserPosition = prepareUserPosition;
    }

    public String getCheckedUserPosition() {
        return checkedUserPosition;
    }

    public void setCheckedUserPosition(String checkedUserPosition) {
        this.checkedUserPosition = checkedUserPosition;
    }

    public String getApprovedUserPosition() {
        return approvedUserPosition;
    }

    public void setApprovedUserPosition(String approvedUserPosition) {
        this.approvedUserPosition = approvedUserPosition;
    }

    public byte[] getPreparedSignature() {
        return preparedSignature;
    }

    public void setPreparedSignature(byte[] preparedSignature) {
        this.preparedSignature = preparedSignature;
    }

    public byte[] getCheckedSignature() {
        return checkedSignature;
    }

    public void setCheckedSignature(byte[] checkedSignature) {
        this.checkedSignature = checkedSignature;
    }

    public byte[] getApprovedSignature() {
        return approvedSignature;
    }

    public void setApprovedSignature(byte[] approvedSignature) {
        this.approvedSignature = approvedSignature;
    }
}
