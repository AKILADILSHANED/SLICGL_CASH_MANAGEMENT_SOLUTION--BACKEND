package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.searchAccountUpdateDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class searchAccountUpdate implements RowMapper<searchAccountUpdateDTO> {
    @Override
    public searchAccountUpdateDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        searchAccountUpdateDTO accountSearch = new searchAccountUpdateDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getString(6),
                rs.getInt(7),
                rs.getString(8),
                rs.getObject(9, LocalDateTime.class),
                rs.getString(10),
                rs.getInt(11)
                );

        return accountSearch;
    }
}
