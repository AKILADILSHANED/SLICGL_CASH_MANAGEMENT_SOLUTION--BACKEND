package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.repoListForManualTransfersDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class repoListForManualTransferMapper implements RowMapper<repoListForManualTransfersDTO> {
    @Override
    public repoListForManualTransfersDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new repoListForManualTransfersDTO(
                rs.getString(1),
                rs.getString(2)
        );
    }
}
