package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getVoucherDetailsDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class getVoucherDetailsMapper implements RowMapper<getVoucherDetailsDTO> {
    @Override
    public getVoucherDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new getVoucherDetailsDTO(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getBytes(9),
                rs.getBytes(10),
                rs.getBytes(11),
                rs.getString(12),
                rs.getString(13),
                rs.getString(14),
                rs.getInt(15),
                rs.getFloat(16),
                rs.getString(17),
                rs.getString(18),
                rs.getString(19),
                rs.getInt(20)
        );
    }
}
