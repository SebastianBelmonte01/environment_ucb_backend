package bo.ucb.edu.environment.Api;

import bo.ucb.edu.environment.Bl.AuthBl;
import bo.ucb.edu.environment.Bl.EnvironmentBl;
import bo.ucb.edu.environment.Dto.EnvironmentDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EnvironmentApi {
    @Autowired
    private EnvironmentBl environmentBl;
    @Autowired
    private AuthBl authBl;

    @GetMapping("environment")
    public ResponseDto<List<EnvironmentDto>> getEnvironment(@RequestHeader("Authorization") String token){
        ResponseDto<List<EnvironmentDto>> response = new ResponseDto<>();
        if (!authBl.validateToken(token)){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid token");
            return response;
        }
        response.setCode("0000");
        response.setResponse(environmentBl.getAllEnvironments());
        return response;
    }
}
