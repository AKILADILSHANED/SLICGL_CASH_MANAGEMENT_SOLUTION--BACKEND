package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.channelDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class channelDetailsMapper implements RowMapper<channelDetailsDTO> {
    @Override
    public channelDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new channelDetailsDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getObject(5, LocalDateTime.class),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8)
        );
    }
}
