package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getRepoClosingBalancesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getRepoClosingBalancesMapper implements RowMapper<getRepoClosingBalancesDTO> {

    @Override
    public getRepoClosingBalancesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getRepoClosingBalancesDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
        );
    }
}
