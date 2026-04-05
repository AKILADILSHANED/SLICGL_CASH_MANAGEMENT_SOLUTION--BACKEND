package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.passwordIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
@RequestMapping(value = "api/v1/password")
public class passwordController {
    @Autowired
    passwordIMPL passwordIMPL;

    @PutMapping(value = "/unlock-password")
    public ResponseEntity<customAPIResponse<String>> unlockPassword(String userId){
        return passwordIMPL.unlockPassword(userId);
    }

    @PutMapping(value = "/reset-password")
    public ResponseEntity<customAPIResponse<String>> resetPassword(String userId){
        return passwordIMPL.resetPassword(userId);
    }
}
