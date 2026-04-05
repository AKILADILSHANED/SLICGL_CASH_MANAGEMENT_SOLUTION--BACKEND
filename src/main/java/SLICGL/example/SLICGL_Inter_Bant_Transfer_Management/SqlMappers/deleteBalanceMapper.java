package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getBalanceForDeleteDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class deleteBalanceMapper implements RowMapper<getBalanceForDeleteDTO> {
    @Override
    public getBalanceForDeleteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        getBalanceForDeleteDTO deleteBalanceObject = new getBalanceForDeleteDTO();
        deleteBalanceObject.setBalanceId(rs.getString(1));
        deleteBalanceObject.setBank(rs.getString(2));
        deleteBalanceObject.setAccountNumber(rs.getString(3));
        deleteBalanceObject.setBalanceDate(rs.getDate(4).toLocalDate());
        deleteBalanceObject.setBalanceAmount(rs.getFloat(5));
        deleteBalanceObject.setDeleteStatus(rs.getInt(6));
        return deleteBalanceObject;
    }
}
