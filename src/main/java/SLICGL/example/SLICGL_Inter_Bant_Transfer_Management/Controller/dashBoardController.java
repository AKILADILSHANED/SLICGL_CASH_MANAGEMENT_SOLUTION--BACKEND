package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getBalanceDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getForecastingDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getRequestDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getTransactionDetailsDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.dashBoardIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/dash-board")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class dashBoardController {
    @Autowired
    dashBoardIMPL dashboard;

    @GetMapping(value = "/get-Transaction-Details")
    public ResponseEntity<customAPIResponse<getTransactionDetailsDTO>> getTransactionDetails(){
        return dashboard.getTransactionDetails();
    }

    @GetMapping(value = "/get-Forecasting-Details")
    public ResponseEntity<customAPIResponse<List<getForecastingDetailsDTO>>> getForecastingDetails(){
        return dashboard.getForecastingDetails();
    }

    @GetMapping(value = "/get-request-Details")
    public ResponseEntity<customAPIResponse<List<getRequestDetailsDTO>>> getFundRequestDetails(){
        return dashboard.getFundRequestDetails();
    }

    @GetMapping(value = "/get-Balance-Details")
    public ResponseEntity<customAPIResponse<List<getBalanceDetailsDTO>>> getBalanceDetails(){
        return dashboard.getBalanceDetails();
    }
}
