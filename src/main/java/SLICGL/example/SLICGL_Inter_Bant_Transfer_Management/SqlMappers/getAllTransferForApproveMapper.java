package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getAllTransferForApproveDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getAllTransfersForCheckDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class getAllTransferForApproveMapper implements RowMapper<getAllTransferForApproveDTO> {
    @Override
    public getAllTransferForApproveDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getAllTransferForApproveDTO(
                rs.getString(1),
                rs.getObject(2, LocalDate.class),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getFloat(7)
        );
    }
}
