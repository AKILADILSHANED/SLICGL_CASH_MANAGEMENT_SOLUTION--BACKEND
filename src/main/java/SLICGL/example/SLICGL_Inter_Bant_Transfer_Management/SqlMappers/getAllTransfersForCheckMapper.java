package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getAllTransfersForCheckDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class getAllTransfersForCheckMapper implements RowMapper<getAllTransfersForCheckDTO> {
    @Override
    public getAllTransfersForCheckDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getAllTransfersForCheckDTO(
                rs.getString(1),
                rs.getObject(2, LocalDate.class),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getFloat(7)
        );
    }
}
