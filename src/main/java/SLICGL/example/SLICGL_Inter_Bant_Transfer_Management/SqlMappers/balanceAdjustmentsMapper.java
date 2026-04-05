package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.balanceAdjustmentsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class balanceAdjustmentsMapper implements RowMapper<balanceAdjustmentsDTO> {
    @Override
    public balanceAdjustmentsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new balanceAdjustmentsDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getFloat(3),
                rs.getString(4),
                rs.getFloat(5),
                rs.getFloat(6),
                rs.getString(7),
                rs.getString(8)
        );
    }
}
