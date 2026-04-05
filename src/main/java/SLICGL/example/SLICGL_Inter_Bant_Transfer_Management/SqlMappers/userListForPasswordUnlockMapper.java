package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.userListForPasswordUnlockDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class userListForPasswordUnlockMapper implements RowMapper<userListForPasswordUnlockDTO> {
    @Override
    public userListForPasswordUnlockDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new userListForPasswordUnlockDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5)
        );
    }
}
