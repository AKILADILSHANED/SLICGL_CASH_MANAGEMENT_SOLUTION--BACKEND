package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getRequestBalancesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getRequestBalancesMapper implements RowMapper<getRequestBalancesDTO> {
    @Override
    public getRequestBalancesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getRequestBalancesDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3)
        );
    }
}
