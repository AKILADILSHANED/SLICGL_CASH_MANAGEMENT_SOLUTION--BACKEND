package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getFundRequestForApproveDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getFundRequestForReverseDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class getFundRequestForReverseMapper implements RowMapper<getFundRequestForReverseDTO> {
    @Override
    public getFundRequestForReverseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getFundRequestForReverseDTO(
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
