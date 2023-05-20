package bo.ucb.edu.environment.Api;

import bo.ucb.edu.environment.Bl.AuthBl;
import bo.ucb.edu.environment.Bl.RequestBl;
import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RequestApi {
    @Autowired
    private AuthBl authBl;
    @Autowired
    private RequestBl requestBl;

    @PostMapping("/request")
    public ResponseDto<RequestDto> createRequest(@RequestHeader Map<String, String> headers, @RequestBody RequestDto requestDto) throws Exception{
        ResponseDto<RequestDto> response = new ResponseDto<>();
        String token = authBl.getTokenFromHeader(headers);
        int id = authBl.getUserIdFromToken(token);
        if(!authBl.validateToken(token)){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        response.setCode("0000");
        response.setResponse(requestBl.createRequest(requestDto, id));
        return response;
    }

    @GetMapping("/request/last")
    public ResponseDto<RequestDto> getRequest (@RequestHeader  Map<String, String> headers) throws Exception{
        ResponseDto<RequestDto> response = new ResponseDto<>();
        String token = authBl.getTokenFromHeader(headers);
        int id = authBl.getUserIdFromToken(token);
        if(!authBl.validateToken(token)){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        response.setCode("0000");
        response.setResponse(requestBl.getLastRequest(id));
        return response;
    }


}
