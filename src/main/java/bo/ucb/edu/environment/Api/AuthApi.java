package bo.ucb.edu.environment.Api;

import bo.ucb.edu.environment.Bl.AuthBl;
import bo.ucb.edu.environment.Dto.ChangePasswordDto;
import bo.ucb.edu.environment.Dto.LoginDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import bo.ucb.edu.environment.Dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {
    @Autowired
    private AuthBl authBl;

    @PostMapping("/login")
    public ResponseDto<TokenDto> login(@RequestBody LoginDto login) {
        ResponseDto<TokenDto> response = new ResponseDto<>();
        TokenDto tokenDto = authBl.login(login);
        if (tokenDto == null) {
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
        } else {
            response.setCode("0000");
            response.setResponse(tokenDto);
        }
        return response;
    }

    @PostMapping("/api/v1/auth/forgot-password")
    public ResponseDto<String> forgotPassword(@RequestBody LoginDto loginDto) {
        ResponseDto<String> response = new ResponseDto<>();
        boolean message = authBl.forgotPassword(loginDto);
        if (message == false) {
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid username");
        } else {
            response.setCode("0000");
            response.setResponse("Email sent");
        }
        return response;
    }

    @PostMapping("/api/v1/auth/verify-code")
    public ResponseDto<String> verifyCode(@RequestBody Map<String, String> requestBody) {
        String code = requestBody.get("code");
        ResponseDto<String> response = new ResponseDto<>();
        boolean message = authBl.verifyCode(code);
        if (message == false) {
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid code");
        } else {
            response.setCode("0000");
            response.setResponse("Code verified");
        }
        return response;
    }

    @PostMapping("/api/v1/auth/change-password")
    public ResponseDto<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        ResponseDto<String> response = new ResponseDto<>();
        boolean message = authBl.changePassword(changePasswordDto);
        if (message == false) {
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid password");
        } else {
            response.setCode("0000");
            response.setResponse("Password changed");
        }
        return response;
    }

}
