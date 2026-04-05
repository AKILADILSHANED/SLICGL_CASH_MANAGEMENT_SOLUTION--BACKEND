package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.transferHistoryDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class transferHistoryMapper implements RowMapper<transferHistoryDTO> {
    @Override
    public transferHistoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new transferHistoryDTO(
                rs.getString(1),
                rs.getObject(2, LocalDate.class),
                rs.getFloat(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getString(9),
                rs.getString(10),
                rs.getString(11)
        );
    }
}
