package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.searchRequestForUpdateDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class searchRequestForUpdateMapper implements RowMapper<searchRequestForUpdateDTO> {

    @Override
    public searchRequestForUpdateDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        searchRequestForUpdateDTO requestObj = new searchRequestForUpdateDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getFloat(4),
                rs.getObject(5, LocalDateTime.class),
                rs.getObject(6, LocalDate.class),
                rs.getString(7),
                rs.getString(8),
                null,
                null
        );
        return requestObj;
    }
}
