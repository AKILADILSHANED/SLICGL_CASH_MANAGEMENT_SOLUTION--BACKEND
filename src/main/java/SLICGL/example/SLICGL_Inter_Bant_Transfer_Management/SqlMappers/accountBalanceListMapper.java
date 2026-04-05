package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.accountBalanceListDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class accountBalanceListMapper implements RowMapper<accountBalanceListDTO> {
    @Override
    public accountBalanceListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new accountBalanceListDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getBigDecimal(4)
        );
    }
}
