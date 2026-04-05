package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getOptionForDeleteDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getOptionForDeleteMapper implements RowMapper<getOptionForDeleteDTO> {
    @Override
    public getOptionForDeleteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getOptionForDeleteDTO(
                rs.getString(1),
                rs.getDate(2).toLocalDate(),
                rs.getString(3),
                rs.getInt(4),
                rs.getInt(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9)
        );
    }
}
