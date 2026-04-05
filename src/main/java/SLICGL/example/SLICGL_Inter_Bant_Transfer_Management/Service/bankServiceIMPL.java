package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bank;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.HttpRequestUtil.HttpRequestUtil;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.bankRepo;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bankServiceIMPL implements bankService {
    @Autowired
    bankRepo bankRepository;
    @Autowired
    HttpSession session;

    private static final Logger logger = LoggerFactory.getLogger(bankServiceIMPL.class);

    @Override
    @Session()
    @LogActivity(methodDescription = "This method will display all registered banks")
    public ResponseEntity<customAPIResponse<List<bank>>> getAllBankList() {
        List<bank> bankList = bankRepository.findAll();
        if (bankList.isEmpty()) {
            customAPIResponse<List<bank>> response = new customAPIResponse<>(
                    false,
                    "No registered bank details found.",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            customAPIResponse<List<bank>> response = new customAPIResponse<>(
                    true,
                    null,
                    bankList
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}
