package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.setPriorityLevelDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class setPriorityLevelMapper implements RowMapper<setPriorityLevelDTO> {
    @Override
    public setPriorityLevelDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new setPriorityLevelDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4)
        );
    }
}
