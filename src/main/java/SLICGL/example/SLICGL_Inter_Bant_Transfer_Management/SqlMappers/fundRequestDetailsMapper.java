package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getFundRequestDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class fundRequestDetailsMapper implements RowMapper<getFundRequestDetailsDTO> {
    @Override
    public getFundRequestDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        getFundRequestDetailsDTO requestObj = new getFundRequestDetailsDTO();

        requestObj.setRequestId(rs.getString(1));
        requestObj.setAccountNumber(rs.getNString(2));
        requestObj.setPaymentType(rs.getString(3));
        requestObj.setRequestAmount(rs.getFloat(4));
        requestObj.setRequestDate(rs.getObject(5,LocalDateTime.class));
        requestObj.setRequiredDate(rs.getObject(6, LocalDate.class));
        requestObj.setApproveStatus(rs.getString(7));
        requestObj.setApprovedBy(rs.getString(8));
        requestObj.setDeleteStatus(rs.getString(9));
        requestObj.setDeleted_by(rs.getString(10));
        requestObj.setRequestedBy(rs.getNString(11));
        requestObj.setRequestType(0);

        return requestObj;
    }
}
