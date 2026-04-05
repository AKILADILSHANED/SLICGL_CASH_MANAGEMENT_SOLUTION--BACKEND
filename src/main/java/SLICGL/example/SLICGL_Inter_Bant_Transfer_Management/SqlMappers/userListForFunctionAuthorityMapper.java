package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.usersForFunctionAuthorityDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class userListForFunctionAuthorityMapper implements RowMapper<usersForFunctionAuthorityDTO> {

    @Override
    public usersForFunctionAuthorityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new usersForFunctionAuthorityDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
        );
    }
}
