package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getRepoAccountsListDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getRepoAccountsListMapper implements RowMapper<getRepoAccountsListDTO> {

    @Override
    public getRepoAccountsListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getRepoAccountsListDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3)
        );
    }
}
