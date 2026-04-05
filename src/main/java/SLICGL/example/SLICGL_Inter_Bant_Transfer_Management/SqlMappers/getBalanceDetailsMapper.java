package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getBalanceDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getBalanceDetailsMapper implements RowMapper<getBalanceDetailsDTO> {
    @Override
    public getBalanceDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getBalanceDetailsDTO(
                rs.getString(1),
                rs.getFloat(2),
                rs.getFloat(3)
        );
    }
}
