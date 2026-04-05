package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.paymentReportDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class paymentReportMapper implements RowMapper<paymentReportDTO> {
    @Override
    public paymentReportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new paymentReportDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6)
        );
    }
}
