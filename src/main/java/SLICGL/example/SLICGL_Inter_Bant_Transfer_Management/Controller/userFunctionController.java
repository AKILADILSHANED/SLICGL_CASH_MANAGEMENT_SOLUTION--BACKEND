package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Controller;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.APIResponse.customAPIResponse;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.grantFunctionDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO.revokeFunctionDTO;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Service.userFunctionIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/userFunction")
@CrossOrigin(value = "${frontend.url}", allowCredentials = "true")
public class userFunctionController {
    @Autowired
    userFunctionIMPL userFunctionIMPL;
    @PostMapping(value = "/grantFunction")
    public ResponseEntity<customAPIResponse<String>> grantFunction(@RequestBody grantFunctionDTO grantFunctionDTO){
        return userFunctionIMPL.grantFunction(grantFunctionDTO);
    }

    @PostMapping(value = "/revokeFunction")
    public ResponseEntity<customAPIResponse<String>> revokeFunction(@RequestBody revokeFunctionDTO revokeFunctionDTO){
        return userFunctionIMPL.revokeFunction(revokeFunctionDTO);
    }
}
