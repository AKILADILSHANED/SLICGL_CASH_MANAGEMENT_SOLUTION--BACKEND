package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getRequestDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class getRequestDetailsMapper implements RowMapper<getRequestDetailsDTO> {
    @Override
    public getRequestDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getRequestDetailsDTO(
                rs.getString(1),
                rs.getBigDecimal(2),
                rs.getString(3),
                rs.getObject(4, LocalDate.class),
                rs.getObject(5, LocalDate.class),
                rs.getString(6)
        );
    }
}
