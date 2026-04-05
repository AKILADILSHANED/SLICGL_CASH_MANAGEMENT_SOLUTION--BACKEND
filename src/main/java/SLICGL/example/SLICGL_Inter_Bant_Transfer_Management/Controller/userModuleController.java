package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getFunctionsForGrantDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.getUserModuleDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.userModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/module")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class userModuleController {
    @Autowired
    userModuleService userModuleService;

    @GetMapping(value = "/get-module-list-grant-authority")
    public ResponseEntity<customAPIResponse<List<getUserModuleDTO>>> getUserModuleForAuthorityGrant() {
        return userModuleService.getUserModuleForAuthorityGrant();
    }

    @GetMapping(value = "/get-module-list-revoke-authority")
    public ResponseEntity<customAPIResponse<List<getUserModuleDTO>>> getUserModuleForAuthorityRevoke() {
        return userModuleService.getUserModuleForAuthorityRevoke();
    }

    @GetMapping(value = "/getFunctionsForGrant")
    public ResponseEntity<customAPIResponse<List<getFunctionsForGrantDTO>>> getFunctionsForGrant(String userId, String moduleId) {
        return userModuleService.getFunctionsForGrant(userId, moduleId);
    }

    @GetMapping(value = "/getFunctionsForRevoke")
    public ResponseEntity<customAPIResponse<List<getFunctionsForGrantDTO>>> getFunctionsForRevoke(String userId, String moduleId) {
        return userModuleService.getFunctionsForRevoke(userId, moduleId);
    }
}
