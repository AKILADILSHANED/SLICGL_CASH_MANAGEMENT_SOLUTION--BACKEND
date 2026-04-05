package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getChannelsForDefineOptionsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getChannelsForDefineOptionsMapper implements RowMapper<getChannelsForDefineOptionsDTO> {
    @Override
    public getChannelsForDefineOptionsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getChannelsForDefineOptionsDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3)
        );
    }
}
