package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getTransferDataForReversalDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class getTransferDataForReversalMapper implements RowMapper<getTransferDataForReversalDTO> {
    @Override
    public getTransferDataForReversalDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getTransferDataForReversalDTO(
                rs.getObject(1, LocalDate.class),
                rs.getString(2),
                rs.getInt(3),
                rs.getInt(4)
        );
    }
}
