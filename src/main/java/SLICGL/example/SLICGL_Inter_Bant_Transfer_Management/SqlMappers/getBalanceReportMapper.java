package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getBalanceReportDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getBalanceReportMapper implements RowMapper<getBalanceReportDTO> {
    @Override
    public getBalanceReportDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getBalanceReportDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getBigDecimal(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9)
        );
    }
}
