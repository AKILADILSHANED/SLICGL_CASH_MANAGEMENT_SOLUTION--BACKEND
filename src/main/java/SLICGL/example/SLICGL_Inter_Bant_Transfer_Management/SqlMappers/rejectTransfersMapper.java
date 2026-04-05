package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.rejectTransfersDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class rejectTransfersMapper implements RowMapper<rejectTransfersDTO> {
    @Override
    public rejectTransfersDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new rejectTransfersDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getFloat(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9),
                rs.getString(10),
                rs.getInt(11)
        );
    }
}
