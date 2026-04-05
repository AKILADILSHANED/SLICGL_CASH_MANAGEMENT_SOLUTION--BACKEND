package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.fundRequestDeleteDTO;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class fundRequestDeleteMapper implements RowMapper<fundRequestDeleteDTO> {

    @Override
    public fundRequestDeleteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        fundRequestDeleteDTO deleteRequest = new fundRequestDeleteDTO(
                rs.getString(1),
                rs.getNString(2),
                rs.getString(3),
                rs.getFloat(4),
                rs.getObject(5, LocalDateTime.class),
                rs.getObject(6, LocalDate.class),
                null
        );
        return deleteRequest;
    }
}
