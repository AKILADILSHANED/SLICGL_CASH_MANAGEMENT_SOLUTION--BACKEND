package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.toAccountsForTransferOptionsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class displayAvailableTransferOptionsMapper implements RowMapper<toAccountsForTransferOptionsDTO> {
    @Override
    public toAccountsForTransferOptionsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new toAccountsForTransferOptionsDTO(
                rs.getString(1),
                rs.getString(2)
        );
    }
}
