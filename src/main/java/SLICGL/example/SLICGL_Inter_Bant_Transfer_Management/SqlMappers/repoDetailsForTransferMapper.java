package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.repoDetailsForTransferDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class repoDetailsForTransferMapper implements RowMapper<repoDetailsForTransferDTO> {
    @Override
    public repoDetailsForTransferDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new repoDetailsForTransferDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getBigDecimal(3)
        );
    }
}
