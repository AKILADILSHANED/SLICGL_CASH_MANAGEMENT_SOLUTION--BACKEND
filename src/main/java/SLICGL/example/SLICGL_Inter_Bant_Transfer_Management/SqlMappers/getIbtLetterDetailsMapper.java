package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getIbtLetterDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getIbtLetterDetailsMapper implements RowMapper<getIbtLetterDetailsDTO> {
    @Override
    public getIbtLetterDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getIbtLetterDetailsDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getBigDecimal(7),
                rs.getString(8)
        );
    }
}
