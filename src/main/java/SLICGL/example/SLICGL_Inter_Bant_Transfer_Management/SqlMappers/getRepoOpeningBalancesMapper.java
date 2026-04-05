package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getRepoOpeningBalancesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getRepoOpeningBalancesMapper implements RowMapper<getRepoOpeningBalancesDTO> {
    @Override
    public getRepoOpeningBalancesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getRepoOpeningBalancesDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3)
        );
    }
}
