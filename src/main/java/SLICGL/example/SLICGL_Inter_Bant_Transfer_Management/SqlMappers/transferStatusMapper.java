package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.transferStatusDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class transferStatusMapper implements RowMapper<transferStatusDTO> {
    @Override
    public transferStatusDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new transferStatusDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3)
        );
    }
}
