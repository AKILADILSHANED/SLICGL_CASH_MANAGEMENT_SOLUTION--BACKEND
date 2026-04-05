package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getTransferForTransferIdDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.excelExportServiceIMPL;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/excel-export")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class excelExportController {
    @Autowired
    excelExportServiceIMPL excelExport;
    @GetMapping("/je-export/excel")
    public void getTransactionDetails(HttpServletResponse response, @RequestParam LocalDate date){
        try {
            // Set response headers for Excel download
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition",
                    "attachment; filename=transactions_" + date + ".xlsx");

            // Get data from service layer and generate Excel
            Workbook workbook = excelExport.getTransactionDetails(date);

            // Write Excel to response output stream
            workbook.write(response.getOutputStream());
            workbook.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error generating Excel file for date: " + date, e);
        }
    }
}
