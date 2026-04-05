package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.deactivateOptionsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class deactivateOptionsMapper implements RowMapper<deactivateOptionsDTO> {
    @Override
    public deactivateOptionsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new deactivateOptionsDTO(
                rs.getString(1),
                rs.getObject(2, LocalDateTime.class),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5)
        );
    }
}
