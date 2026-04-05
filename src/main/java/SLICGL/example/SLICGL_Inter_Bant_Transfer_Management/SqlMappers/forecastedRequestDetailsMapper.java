package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getFundRequestDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class forecastedRequestDetailsMapper implements RowMapper<getFundRequestDetailsDTO> {
    @Override
    public getFundRequestDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        getFundRequestDetailsDTO forecastedObj = new getFundRequestDetailsDTO();

        forecastedObj.setRequestId(rs.getString(1));
        forecastedObj.setAccountNumber(rs.getNString(2));
        forecastedObj.setPaymentType(rs.getString(3));
        forecastedObj.setRequestAmount(rs.getFloat(4));
        forecastedObj.setRequestDate(rs.getObject(5, LocalDateTime.class));
        forecastedObj.setRequiredDate(rs.getObject(6, LocalDate.class));
        forecastedObj.setApproveStatus(null);
        forecastedObj.setApprovedBy(null);
        forecastedObj.setDeleteStatus(rs.getString(7));
        forecastedObj.setDeleted_by(rs.getString(8));
        forecastedObj.setRequestedBy(rs.getNString(9));
        forecastedObj.setRequestType(1);
        return forecastedObj;
    }
}
