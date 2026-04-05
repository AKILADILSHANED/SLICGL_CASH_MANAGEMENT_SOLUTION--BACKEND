package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getTransferListDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getTransferListMapper implements RowMapper<getTransferListDTO> {

    @Override
    public getTransferListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getTransferListDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getFloat(6),
                rs.getString(7)
        );
    }
}
