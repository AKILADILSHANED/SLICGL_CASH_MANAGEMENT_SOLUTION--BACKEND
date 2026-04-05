package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    public ResponseEntity<customAPIResponse<userLoginResponseDTO>> userLogin(userLoginDTO login);

    public ResponseEntity<customAPIResponse<String>> userRegister(userRegisterDTO userRegister);

    public ResponseEntity<customAPIResponse<searchUserDTO>> searchUser(String userId);

    public ResponseEntity<customAPIResponse<searchUserDTO>> searchUserForUpdate(String userId);

    public ResponseEntity<customAPIResponse<updateUserDTO>> updateUser(updateUserDTO updatedUser);

    public ResponseEntity<customAPIResponse<searchUserDTO>> searchUserForDelete(String userId);

    public ResponseEntity<customAPIResponse<String>> deleteUser(String userId);

    public ResponseEntity<customAPIResponse<String>> passwordReset(userPasswordReset resetData);

    public ResponseEntity<customAPIResponse<List<usersForFunctionAuthorityDTO>>> userListForFunctionAuthority();

    public ResponseEntity<customAPIResponse<List<usersForFunctionAuthorityDTO>>> userListForAuthorityRevoke();

    public ResponseEntity<customAPIResponse<List<userListForPasswordUnlockDTO>>> userListForPasswordUnlock();

    public ResponseEntity<customAPIResponse<List<userListForPasswordResetDTO>>> userListForPasswordReset();

    public ResponseEntity<customAPIResponse<Boolean>> userLogout(HttpServletRequest request, HttpServletResponse response);
}
