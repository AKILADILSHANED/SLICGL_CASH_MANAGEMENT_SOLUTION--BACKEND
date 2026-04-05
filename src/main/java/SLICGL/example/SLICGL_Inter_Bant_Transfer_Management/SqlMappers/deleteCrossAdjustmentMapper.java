package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.deleteCrossAdjustmentDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class deleteCrossAdjustmentMapper implements RowMapper<deleteCrossAdjustmentDTO> {
    @Override
    public deleteCrossAdjustmentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new deleteCrossAdjustmentDTO(
                rs.getObject(1, LocalDate.class),
                rs.getString(2)
        );
    }
}
