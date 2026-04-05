package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getTransferForTransferIdDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getTransferForTransferIdMapper implements RowMapper<getTransferForTransferIdDTO> {
    @Override
    public getTransferForTransferIdDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getTransferForTransferIdDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getFloat(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9),
                rs.getInt(10),
                rs.getString(11)
        );
    }
}
