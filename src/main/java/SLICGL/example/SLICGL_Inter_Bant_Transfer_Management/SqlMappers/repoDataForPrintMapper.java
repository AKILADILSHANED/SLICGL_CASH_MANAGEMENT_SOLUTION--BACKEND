package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.repoDataForPrintDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class repoDataForPrintMapper implements RowMapper<repoDataForPrintDTO> {
    @Override
    public repoDataForPrintDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new repoDataForPrintDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8)
        );
    }
}
