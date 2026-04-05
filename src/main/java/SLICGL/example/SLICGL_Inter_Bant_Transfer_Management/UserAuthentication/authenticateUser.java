package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.UserAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class authenticateUser {
    @Autowired
    JdbcTemplate template;

    public boolean hasAuthority(String userId, String functionId){
        try{
            //Check whether user has the authority for function;
            String sql = "SELECT COUNT(*) FROM user_function WHERE function_id = ? AND user_id = ?";
            Integer count = template.queryForObject(sql, Integer.class, functionId, userId);
            return count != null && count > 0;
        }catch (Exception e){
            return false;
        }
    }
}
