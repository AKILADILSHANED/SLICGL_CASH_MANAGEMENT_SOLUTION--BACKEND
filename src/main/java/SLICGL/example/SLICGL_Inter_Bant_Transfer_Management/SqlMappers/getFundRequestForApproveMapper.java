package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getFundRequestForApproveDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class getFundRequestForApproveMapper implements RowMapper<getFundRequestForApproveDTO> {

    @Override
    public getFundRequestForApproveDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getFundRequestForApproveDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getFloat(5),
                rs.getObject(6, LocalDateTime.class),
                rs.getObject(7, LocalDate.class)
        );
    }
}
