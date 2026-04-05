package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.repoDetailsForInvestmentsDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class repoDetailsForInvestmentsMapper implements RowMapper<repoDetailsForInvestmentsDTO> {
    @Override
    public repoDetailsForInvestmentsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new repoDetailsForInvestmentsDTO(
                rs.getObject(1, LocalDate.class),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getBigDecimal(5)
        );
    }
}
