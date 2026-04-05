package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.crossAdjustmentDeleteDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class crossAdjustmentDeleteMapper implements RowMapper<crossAdjustmentDeleteDTO> {
    @Override
    public crossAdjustmentDeleteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new crossAdjustmentDeleteDTO(
                rs.getString(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getObject(6, LocalDate.class),
                rs.getString(7),
                rs.getInt(8),
                rs.getString(9)
        );
    }
}
