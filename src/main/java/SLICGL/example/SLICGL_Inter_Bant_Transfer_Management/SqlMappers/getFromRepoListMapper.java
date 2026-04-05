package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getFromRepoListDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getFromRepoListMapper implements RowMapper<getFromRepoListDTO> {
    @Override
    public getFromRepoListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getFromRepoListDTO(
                rs.getString(1),
                rs.getString(2)
        );
    }
}
