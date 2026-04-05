package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getFunctionsForGrantDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAllSubFunctionsMapper implements RowMapper<getFunctionsForGrantDTO> {
    @Override
    public getFunctionsForGrantDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getFunctionsForGrantDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3)
        );
    }
}
