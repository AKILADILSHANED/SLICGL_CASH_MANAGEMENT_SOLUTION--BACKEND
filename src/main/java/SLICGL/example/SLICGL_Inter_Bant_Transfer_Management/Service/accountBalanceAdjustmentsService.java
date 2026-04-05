package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import java.math.BigDecimal;

public interface accountBalanceAdjustmentsService {
    public String generateAdjustmentId();
    public String newRemark(String remarkAccount, BigDecimal adjustmentAmount);
    public void saveNewAdjustment(String remarkAccount, BigDecimal adjustmentAmount, String adjustmentBalance, String transferId, String crossAdjustmentId);
}
