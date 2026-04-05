package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getTransferDataForReverseAllDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class getTransferDataForReverseAllMapper implements RowMapper<getTransferDataForReverseAllDTO> {
    @Override
    public getTransferDataForReverseAllDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getTransferDataForReverseAllDTO(
                rs.getString(1),
                rs.getObject(2, LocalDate.class),
                rs.getString(3),
                rs.getInt(4),
                rs.getInt(5)
        );
    }
}
