package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.paymentSearchDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class paymentSearchMapper implements RowMapper<paymentSearchDTO> {

    @Override
    public paymentSearchDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        paymentSearchDTO searchObj = new paymentSearchDTO();
        searchObj.setPaymentId(rs.getString(1));
        searchObj.setPaymentType(rs.getString(2));
        searchObj.setRegisteredDate(rs.getObject(3, LocalDateTime.class));
        searchObj.setRegisteredBy(rs.getString(4));
        searchObj.setDeleteStatus(rs.getInt(5));
        return searchObj;
    }
}
