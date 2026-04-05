package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.searchAccountDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class accountSearch implements RowMapper<searchAccountDTO> {
    @Override
    public searchAccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        searchAccountDTO accountSearch = new searchAccountDTO();
        accountSearch.setAccountId(rs.getString(1));
        accountSearch.setBank(rs.getString(2));
        accountSearch.setBankBranch(rs.getString(3));
        accountSearch.setAccountNumber(rs.getString(4));
        accountSearch.setDeleteStatus(rs.getInt(5));
        accountSearch.setAccountType(rs.getString(6));
        accountSearch.setCurrency(rs.getString(7));
        accountSearch.setGlCode(rs.getInt(8));
        accountSearch.setRegisteredDate(rs.getObject(9,LocalDateTime.class));
        accountSearch.setRegisteredBy(rs.getString(10));

        return accountSearch;
    }
}
