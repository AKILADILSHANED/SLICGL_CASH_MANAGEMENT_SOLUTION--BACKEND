package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.SqlMappers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.searchUserDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class userSearchMapper implements RowMapper<searchUserDTO> {
    @Override
    public searchUserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        searchUserDTO userSearchedObject = new searchUserDTO();
        userSearchedObject.setUserId(rs.getNString(1));
        userSearchedObject.setUserFirstName(rs.getNString(2));
        userSearchedObject.setUserLastName(rs.getNString(3));
        userSearchedObject.setUserEpf(rs.getNString(4));
        userSearchedObject.setUserEmail(rs.getNString(5));
        userSearchedObject.setUserActiveStatus(rs.getString(6));
        userSearchedObject.setUserLevel(rs.getNString(7));
        userSearchedObject.setUserCreatedDate((LocalDateTime) rs.getObject(8));
        userSearchedObject.setUserCreateBy(rs.getNString(9));
        userSearchedObject.setUserPosition(rs.getNString(10));
        userSearchedObject.setDepartment(rs.getNString(11));
        userSearchedObject.setSection(rs.getNString(12));
        return userSearchedObject;
    }
}
