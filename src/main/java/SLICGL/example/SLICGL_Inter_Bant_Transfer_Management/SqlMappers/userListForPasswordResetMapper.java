package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.userListForPasswordResetDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class userListForPasswordResetMapper implements RowMapper<userListForPasswordResetDTO> {
    @Override
    public userListForPasswordResetDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new userListForPasswordResetDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5)
        );
    }
}
