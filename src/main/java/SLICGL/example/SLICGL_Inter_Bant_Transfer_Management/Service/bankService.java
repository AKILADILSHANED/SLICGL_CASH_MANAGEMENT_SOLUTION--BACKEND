package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Entity.bank;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface bankService {
    public ResponseEntity<customAPIResponse<List<bank>>> getAllBankList();
}
