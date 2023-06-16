package bo.ucb.edu.environment.Api;

import bo.ucb.edu.environment.Bl.AuthBl;
import bo.ucb.edu.environment.Bl.ClaimBl;
import bo.ucb.edu.environment.Dto.ClaimDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ClaimApi {
    @Autowired
    private ClaimBl claimBl;
    @Autowired
    private AuthBl authBl;

    @PostMapping("/claim/reservation/{reservationId}")
    public ResponseDto<String> saveNewClaim(@RequestHeader Map<String, String> headers, @PathVariable Long reservationId, @RequestParam("newClaim") String newClaim, @RequestParam("imageFile") MultipartFile file) throws Exception {
        ResponseDto<String> response = new ResponseDto<>();
        String token = authBl.getTokenFromHeader(headers);
        int id = authBl.getUserIdFromToken(token);
        if (!authBl.validateToken(token)) {
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        response.setCode("0000");
        byte[] imageData = file.getBytes();
        claimBl.saveNewClaim(newClaim, imageData, reservationId);
        response.setResponse("Se guardo reclamo");
        return response;
    }







}
