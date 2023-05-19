package bo.ucb.edu.environment.Api;

import bo.ucb.edu.environment.Bl.AuthBl;
import bo.ucb.edu.environment.Bl.ProfessorBl;
import bo.ucb.edu.environment.Dto.ProfessorDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import bo.ucb.edu.environment.Dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/professor")
public class ProfessorApi {
    @Autowired
    private ProfessorBl professorBl;

    @GetMapping("/subject")
    public ResponseDto<ProfessorDto> getProfessor(@RequestHeader Map<String, String> headers) throws Exception {
        ResponseDto<ProfessorDto> response = new ResponseDto<>();
        AuthBl authBl = new AuthBl();
        String token = authBl.getTokenFromHeader(headers);
        int id = authBl.getUserIdFromToken(token);
        if (token == null) {
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
        } else {
            response.setCode("0000");
            response.setResponse(professorBl.getProfessor((long) id));
        }
        return response;
    }

}
