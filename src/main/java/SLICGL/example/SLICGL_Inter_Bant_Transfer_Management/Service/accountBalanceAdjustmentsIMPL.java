package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.accountBalanceAdjustments;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.crossAdjustments;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.transfers;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.accountBalanceAdjustmentsRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.accountBalanceRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.crossAdjustmentRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.transfersRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class accountBalanceAdjustmentsIMPL implements accountBalanceAdjustmentsService {

    @Autowired
    accountBalanceAdjustmentsRepo accountBalanceAdjustmentsRepository;

    @Autowired
    accountBalanceRepo accountBalanceRepository;

    @Autowired
    transfersRepo transfersRepository;

    @Autowired
    crossAdjustmentRepo crossAdjustmentRepo;

    //This method has been implemented to generate a new Adjustment ID.
    @Override
    @Session()
    @Transactional
    public String generateAdjustmentId() {
        //Create new Adjustment ID for a new Adjustment;
        String newAdjustmentId;
        String currentYear = String.valueOf(LocalDate.now().getYear());
        String currentMonth = String.format("%02d", LocalDate.now().getMonthValue());

        //Get last Adjustment ID from Adjustment table;
        String lastAdjustmentId = accountBalanceAdjustmentsRepository.getLastAdjustmentId();
        if (lastAdjustmentId == null) {
            newAdjustmentId = "ADJ-" + currentYear + currentMonth + "-0001";
        } else {
            if ((currentYear + currentMonth).equals(lastAdjustmentId.substring(4, 10))) {
                int newNumericAdjustmentId = Integer.parseInt(lastAdjustmentId.substring(11, 15)) + 1;
                newAdjustmentId = "ADJ-" + currentYear + currentMonth + String.format("-%04d", newNumericAdjustmentId);
            } else {
                newAdjustmentId = "ADJ-" + currentYear + currentMonth + "-0001";
            }
        }
        return newAdjustmentId;
    }

    //This method has been implemented to Generate proper adjustment remark;
    @Override
    @Session()
    public String newRemark(String remarkAccount, BigDecimal adjustmentAmount) {
        if (adjustmentAmount.compareTo(BigDecimal.ZERO) > 0) {
            return "Received from " + remarkAccount;
        } else {
            return "Transferred to " + remarkAccount;
        }
    }

    //This method has been implemented to save a new Account Balance Adjustment to the adjustment table;
    @Override
    @Session()
    @Transactional
    public void saveNewAdjustment(String remarkAccount, BigDecimal adjustmentAmount, String adjustmentBalance, String transferId, String crossAdjustmentId) {
        transfers transferEntity = (transferId != null) ? transfersRepository.findById(transferId).orElse(null) : null;
        crossAdjustments crossAdjustment = (crossAdjustmentId != null) ? crossAdjustmentRepo.findById(crossAdjustmentId).orElse(null) : null;
        accountBalanceAdjustmentsRepository.save(
                new accountBalanceAdjustments(
                        this.generateAdjustmentId(),
                        LocalDateTime.now(),
                        this.newRemark(remarkAccount, adjustmentAmount),
                        adjustmentAmount,
                        accountBalanceRepository.findById(adjustmentBalance).get(),
                        transferEntity,
                        crossAdjustment
                )
        );
    }
}
