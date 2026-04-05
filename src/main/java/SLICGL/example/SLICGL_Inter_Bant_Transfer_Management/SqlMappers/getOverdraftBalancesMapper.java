package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getOverdraftBalancesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getOverdraftBalancesMapper implements RowMapper<getOverdraftBalancesDTO> {
    @Override
    public getOverdraftBalancesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getOverdraftBalancesDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3)
        );
    }
}
