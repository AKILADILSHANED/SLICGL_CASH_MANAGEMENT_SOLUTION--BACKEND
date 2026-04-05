package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import org.apache.poi.ss.usermodel.Workbook;

import java.time.LocalDate;

public interface excelExportService {
    public Workbook getTransactionDetails(LocalDate date);
}
