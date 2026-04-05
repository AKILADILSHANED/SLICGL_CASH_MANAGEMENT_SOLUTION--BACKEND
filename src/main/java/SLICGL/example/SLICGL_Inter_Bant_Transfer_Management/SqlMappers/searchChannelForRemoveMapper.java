package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.channelSearchForRemoveDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class searchChannelForRemoveMapper implements RowMapper<channelSearchForRemoveDTO> {
    @Override
    public channelSearchForRemoveDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new channelSearchForRemoveDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getInt(5),
                rs.getObject(6, LocalDateTime.class),
                rs.getString(7)
        );
    }
}
