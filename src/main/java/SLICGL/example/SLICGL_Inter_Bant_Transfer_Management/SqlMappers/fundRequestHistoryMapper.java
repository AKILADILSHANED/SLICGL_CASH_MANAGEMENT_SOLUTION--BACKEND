package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.fundRequestHistoryDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class fundRequestHistoryMapper implements RowMapper<fundRequestHistoryDTO> {
    @Override
    public fundRequestHistoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new fundRequestHistoryDTO(
                rs.getString(1),
                rs.getObject(2, LocalDateTime.class),
                rs.getObject(3, LocalDate.class),
                rs.getFloat(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8)
        );
    }
}
