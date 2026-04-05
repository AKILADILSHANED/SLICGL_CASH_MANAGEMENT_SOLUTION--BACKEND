package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getCrossAdjustmentDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class getCrossAdjustmentDetailsMapper implements RowMapper<getCrossAdjustmentDetailsDTO> {
    @Override
    public getCrossAdjustmentDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getCrossAdjustmentDetailsDTO(
                rs.getString(1),
                rs.getObject(2, LocalDate.class),
                rs.getString(3)
        );
    }
}
