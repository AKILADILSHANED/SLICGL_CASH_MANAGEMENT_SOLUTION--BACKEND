package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getBalanceForUpdateDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class updateBalanceMapper implements RowMapper<getBalanceForUpdateDTO> {

    @Override
    public getBalanceForUpdateDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        getBalanceForUpdateDTO updateBalance = new getBalanceForUpdateDTO();
        updateBalance.setBalanceId(rs.getString(1));
        updateBalance.setBank(rs.getString(2));
        updateBalance.setAccountNumber(rs.getString(3));
        updateBalance.setBalanceDate(rs.getDate(4).toLocalDate());
        updateBalance.setBalanceAmount(rs.getFloat(5));
        updateBalance.setDeleteStatus(rs.getInt(6));
        return updateBalance;
    }
}
