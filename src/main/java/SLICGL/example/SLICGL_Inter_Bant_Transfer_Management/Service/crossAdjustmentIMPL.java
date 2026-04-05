package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.crossAdjustments;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.UserRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.crossAdjustmentRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class crossAdjustmentIMPL implements crossAdjustmentService {
    @Autowired
    crossAdjustmentRepo crossAdjustmentRepository;

    @Autowired
    HttpSession session;

    @Autowired
    UserRepo UserRepository;

    @Override
    @Session()
    @RequiresPermission(value = "FUNC-024")
    @LogActivity(methodDescription = "This method will generate a new cross adjustment id")
    public String generateNewCrossAdjustmentId() {
        //Create new  Cross Adjustment ID;
        String newAdjustmentId;
        String currentYear = String.valueOf(LocalDate.now().getYear());
        String currentMonth = String.format("%02d", LocalDate.now().getMonthValue());

        //Get last Cross Adjustment ID from Cross_Adjustment table;
        String lastAdjustmentId = crossAdjustmentRepository.getLastAdjustmentId();
        if (lastAdjustmentId == null) {
            newAdjustmentId = "CRSADJ-" + currentYear + currentMonth + "-0001";
        } else {
            if ((currentYear + currentMonth).equals(lastAdjustmentId.substring(7, 13))) {
                int newNumericAdjustmentId = Integer.parseInt(lastAdjustmentId.substring(14, 18)) + 1;
                newAdjustmentId = "CRSADJ-" + currentYear + currentMonth + String.format("-%04d", newNumericAdjustmentId);
            } else {
                newAdjustmentId = "CRSADJ-" + currentYear + currentMonth + "-0001";
            }
        }
        return newAdjustmentId;
    }

    @Transactional
    @Override
    @Session()
    @RequiresPermission(value = "FUNC-024")
    @LogActivity(methodDescription = "This method will save new cross adjustment")
    public String saveNewCrossAdjustment() {
        try {
            //Get new cross adjustment id;
            String generatedAdjustmentId = this.generateNewCrossAdjustmentId();
            crossAdjustments adjustmentEntity = new crossAdjustments(generatedAdjustmentId, LocalDateTime.now(), 0, null);
            crossAdjustmentRepository.save(adjustmentEntity);
            return generatedAdjustmentId;
        } catch (Exception e) {
            return "Error occurred while generating new cross adjustment id";
        }
    }

    @Override
    @Session()
    public ResponseEntity<customAPIResponse<List<String>>> getCrossAdjustmentList(String balanceId) {
        try {
            List<String> adjustmentList = crossAdjustmentRepository.getCrossAdjustmentList(balanceId);
            if (!adjustmentList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(new customAPIResponse<>(
                        true,
                        null,
                        adjustmentList
                ));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new customAPIResponse<>(
                        false,
                        "No Adjustments can be found for provided Balance ID!",
                        null
                ));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new customAPIResponse<>(
                    false,
                    "Un-expected error occurred. Please contact the administrator!",
                    null
            ));
        }
    }
}
