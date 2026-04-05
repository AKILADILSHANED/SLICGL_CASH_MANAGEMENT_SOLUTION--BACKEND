package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getForecastingDetailsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class getForecastingDetailsMapper implements RowMapper<getForecastingDetailsDTO> {

    private final Float totalForecastAmount;

    // Constructor to receive the total amount
    public getForecastingDetailsMapper(Float totalForecastAmount) {
        this.totalForecastAmount = totalForecastAmount;
    }
    @Override
    public getForecastingDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getForecastingDetailsDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getFloat(3),
                rs.getObject(4, LocalDate.class),
                rs.getString(5),
                this.totalForecastAmount
        );
    }
}
