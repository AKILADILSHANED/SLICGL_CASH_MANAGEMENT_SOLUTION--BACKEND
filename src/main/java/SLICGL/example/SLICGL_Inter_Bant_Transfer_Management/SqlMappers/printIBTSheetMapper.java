package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.printIBTSheetDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class printIBTSheetMapper implements RowMapper<printIBTSheetDTO> {
    @Override
    public printIBTSheetDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new printIBTSheetDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getFloat(6),
                rs.getString(7)
        );
    }
}
