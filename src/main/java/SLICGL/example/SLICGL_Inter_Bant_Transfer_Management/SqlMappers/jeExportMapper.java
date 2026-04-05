package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.transferDataForSAPDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class jeExportMapper implements RowMapper<transferDataForSAPDTO> {
    @Override
    public transferDataForSAPDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new transferDataForSAPDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getFloat(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7)
        );
    }
}
