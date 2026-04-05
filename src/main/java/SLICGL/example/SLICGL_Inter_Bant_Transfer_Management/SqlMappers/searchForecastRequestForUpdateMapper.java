package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.searchRequestForUpdateDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class searchForecastRequestForUpdateMapper implements RowMapper<searchRequestForUpdateDTO> {

    @Override
    public searchRequestForUpdateDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        searchRequestForUpdateDTO forecastObj = new searchRequestForUpdateDTO(
                rs.getString("request_id"),
                rs.getString("account_id"),
                rs.getString("payment_id"),
                rs.getFloat("request_amount"),
                rs.getObject("request_date", LocalDateTime.class),
                rs.getObject("required_date", LocalDate.class),
                null,
                rs.getString("delete_status"),
                null,
                null
        );
        return forecastObj;
    }
}
