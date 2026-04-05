package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.sapJEFileDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.transferDataForSAPDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Security.RequiresPermission;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Session.Session;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers.jeExportMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class excelExportServiceIMPL implements excelExportService {
    @Autowired
    JdbcTemplate template;

    @Override
    @Session()
    @RequiresPermission("FUNC-046")
    @LogActivity(methodDescription = "This method will export IBT sheet into excel")
    public Workbook getTransactionDetails(LocalDate date) {
        //Create new list of sapJEFileDTO;
        List<sapJEFileDTO> sapJEFileDTO = new ArrayList<>();
        //Get transaction details for export file;
        String sql = "SELECT DATE_FORMAT(tfr.transfer_date, '%d.%m.%Y') AS 'Date', fromaccount.account_number AS 'From Reference', toaccount.account_number AS 'To Reference', tfr.transfer_amount AS 'Amount', fromaccount.gl_code AS 'From GL', toaccount.gl_code AS 'ToGL', chnl.short_key FROM transfers tfr LEFT JOIN bank_account fromaccount ON fromaccount.account_id = tfr.from_account LEFT JOIN bank_account toaccount ON toaccount.account_id = tfr.to_account LEFT JOIN transfer_channel chnl ON chnl.channel_id = tfr.chanel WHERE tfr.is_checked = 1 AND tfr.is_approved = 1 AND tfr.is_reversed = 0 AND DATE(tfr.transfer_date) = ?";
        List<transferDataForSAPDTO> transferDataList = template.query(sql, new jeExportMapper(), date);
        int count = 0;
        //Loop each data and prepare according to the sapJEFileDTO class;
        for (transferDataForSAPDTO loopedTransfer : transferDataList) {
            count++;
            //Add debit entry for sapJEFileDTO list;
            sapJEFileDTO.add(
                    new sapJEFileDTO(
                            String.valueOf(count),
                            "1",
                            "5000",
                            "SA",
                            loopedTransfer.getDate().toString(),
                            loopedTransfer.getDate().toString(),
                            loopedTransfer.getFromReference().substring(loopedTransfer.getFromReference().length() - 4) + " to " + loopedTransfer.getToReference().substring(loopedTransfer.getToReference().length() - 4),
                            "IBT",
                            "LKR",
                            "40",
                            null,
                            loopedTransfer.getTransferAmount(),
                            loopedTransfer.getTransferAmount(),
                            LocalDate.parse(loopedTransfer.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                            loopedTransfer.getShortKey(),
                            null,
                            null,
                            loopedTransfer.getDate().toString(),
                            String.valueOf(Integer.parseInt(loopedTransfer.getToGL()) + 1),
                            "50000000",
                            null,
                            null,
                            null,
                            null,
                            "5000",
                            "1000",
                            null,
                            null
                    )
            );

            //Add credit entry for sapJEFileDTO list;
            sapJEFileDTO.add(
                    new sapJEFileDTO(
                            String.valueOf(count),
                            "2",
                            "5000",
                            "SA",
                            loopedTransfer.getDate().toString(),
                            loopedTransfer.getDate().toString(),
                            loopedTransfer.getFromReference().substring(loopedTransfer.getFromReference().length() - 4) + " to " + loopedTransfer.getToReference().substring(loopedTransfer.getToReference().length() - 4),
                            "IBT",
                            "LKR",
                            "50",
                            null,
                            loopedTransfer.getTransferAmount(),
                            loopedTransfer.getTransferAmount(),
                            LocalDate.parse(loopedTransfer.getDate().toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                            loopedTransfer.getShortKey(),
                            null,
                            null,
                            loopedTransfer.getDate().toString(),
                            String.valueOf(Integer.parseInt(loopedTransfer.getFromGL()) + 2),
                            "50000000",
                            null,
                            null,
                            null,
                            null,
                            "5000",
                            "1000",
                            null,
                            null
                    )
            );
        }
        //Excel work book creation;
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("JE_File");
        //Creation of header row in Excel;
        Row headerRow = sheet.createRow(0);
        String[] headerTitles = {"Header ID", "Line Item ID", "Company Code", "Document Type", "Document Date", "Posting Date", "Reference  Blank", "Header Text", "Currency", "Posting Key", "Special GL Indicator", "Amount in Local Currency", "Amount in Document Currency", "Assignment Number", "Line Item Text", "Cost Center", "Order Number", "Value Date", "G/L Account", "Profit Center", "Reference 1", "Reference 2", "Reference 3", "Material Number", "Business Area", "Functional Area", "Customer", "Vendor"};
        for (int i = 0; i < headerTitles.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headerTitles[i]);
        }
        //Set cell values from each data row;
        int rowNum = 1;
        for (sapJEFileDTO sapJE : sapJEFileDTO) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(sapJE.getHeaderId());
            row.createCell(1).setCellValue(sapJE.getLineItemId());
            row.createCell(2).setCellValue(sapJE.getCompanyCode());
            row.createCell(3).setCellValue(sapJE.getDocumentType());
            row.createCell(4).setCellValue(sapJE.getDocumentDate());
            row.createCell(5).setCellValue(sapJE.getPostingDate());
            row.createCell(6).setCellValue(sapJE.getReference());
            row.createCell(7).setCellValue(sapJE.getHeaderText());
            row.createCell(8).setCellValue(sapJE.getCurrency());
            row.createCell(9).setCellValue(sapJE.getPostingKey());
            row.createCell(10).setCellValue(sapJE.getSpecialGLIndicator());
            row.createCell(11).setCellValue(sapJE.getAmountInLocalCurrency());
            row.createCell(12).setCellValue(sapJE.getAmountInDocumentCurrency());
            row.createCell(13).setCellValue(sapJE.getAssignmentNumber());
            row.createCell(14).setCellValue(sapJE.getLineItemText());
            row.createCell(15).setCellValue(sapJE.getCostCenter());
            row.createCell(16).setCellValue(sapJE.getOrderNumber());
            row.createCell(17).setCellValue(sapJE.getValueDate());
            row.createCell(18).setCellValue(sapJE.getGlAccount());
            row.createCell(19).setCellValue(sapJE.getProficCenter());
            row.createCell(20).setCellValue(sapJE.getReference1());
            row.createCell(21).setCellValue(sapJE.getReference2());
            row.createCell(22).setCellValue(sapJE.getReference3());
            row.createCell(23).setCellValue(sapJE.getMaterialNumber());
            row.createCell(24).setCellValue(sapJE.getBusinessArea());
            row.createCell(25).setCellValue(sapJE.getFunctionalArea());
            row.createCell(26).setCellValue(sapJE.getCustomer());
            row.createCell(27).setCellValue(sapJE.getVendor());
        }
        // Auto-size columns
        for (int i = 0; i < headerTitles.length; i++) {
            sheet.autoSizeColumn(i);
        }
        return workbook;
    }
}
