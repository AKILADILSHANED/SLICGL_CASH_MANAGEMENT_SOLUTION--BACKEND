package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getAccountBalancesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getAccountBalancesMapper implements RowMapper<getAccountBalancesDTO> {
    @Override
    public getAccountBalancesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getAccountBalancesDTO(
                rs.getString(1),
                rs.getString(2)
        );
    }
}
