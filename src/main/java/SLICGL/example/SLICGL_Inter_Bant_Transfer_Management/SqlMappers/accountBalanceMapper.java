package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getAllBalancesDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class accountBalanceMapper implements RowMapper<getAllBalancesDTO> {
    @Override
    public getAllBalancesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        getAllBalancesDTO allBalanceObj = new getAllBalancesDTO();
        allBalanceObj.setBalanceId(rs.getString(1));
        allBalanceObj.setBank(rs.getString(2));
        allBalanceObj.setAccountNumber(rs.getString(3));
        allBalanceObj.setBalanceDate(rs.getDate(4).toLocalDate());
        allBalanceObj.setBalanceAmount(rs.getFloat(5));
        allBalanceObj.setDeleteStatus(rs.getString(6));
        allBalanceObj.setDeletedBy(rs.getString(7));
        allBalanceObj.setEnteredBy(rs.getString(8));

        return allBalanceObj;
    }
}
