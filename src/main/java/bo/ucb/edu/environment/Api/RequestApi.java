package bo.ucb.edu.environment.Api;

import bo.ucb.edu.environment.Bl.AuthBl;
import bo.ucb.edu.environment.Bl.RequestBl;
import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.RequestSearchDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import bo.ucb.edu.environment.Utils.ReservationTime;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Transactional
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
        RequestDto sentRequest =  requestBl.createRequest(requestDto, id);
        requestBl.asignEnvironment(sentRequest);
        response.setResponse(sentRequest);
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

    @GetMapping("/request")
    public ResponseDto<List<RequestDto>> getAllRequests(@RequestHeader Map<String, String> headers) throws Exception {
        ResponseDto<List<RequestDto>> response = new ResponseDto<>();
        String token = authBl.getTokenFromHeader(headers);
        int id = authBl.getUserIdFromToken(token);
        if(!authBl.validateToken(token)){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        response.setCode("0000");
        response.setResponse(requestBl.getAllRequests(id));
        return response;
    }

    @GetMapping("/request/pending")
    public ResponseDto<List<RequestDto>> getPendingRequests(@RequestHeader Map<String, String> headers) throws Exception {
        ResponseDto<List<RequestDto>> response = new ResponseDto<>();

        String token = authBl.getTokenFromHeader(headers);
        int id = authBl.getUserIdFromToken(token);
        if(!authBl.validateToken(token)){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        response.setCode("0000");
        response.setResponse(requestBl.getRequestsByState(id, "En Espera"));
        return response;
    }

    @PutMapping("/request/{requestId}")
    public ResponseDto<RequestDto> cancelRequest(@RequestHeader Map<String, String> headers, @PathVariable("requestId") Long requestId) throws Exception {
        ResponseDto response = new ResponseDto<>();
        String token = authBl.getTokenFromHeader(headers);
        int id = authBl.getUserIdFromToken(token);
        if(!authBl.validateToken(token)){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        response.setCode("0000");
        response.setResponse(requestBl.cancelRequest(id, requestId));
        return response;
    }

    @PutMapping("/request/reject/accept/{reservationId}")
    public ResponseDto<RequestDto> rejectRequest(@RequestHeader Map<String, String> headers, @PathVariable("reservationId") Long reservationId) throws Exception {
        ResponseDto response = new ResponseDto<>();
        String token = authBl.getTokenFromHeader(headers);
        int id = authBl.getUserIdFromToken(token);
        if(!authBl.validateToken(token)){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        response.setCode("0000");
        requestBl.acceptRejection(id, reservationId);
        response.setResponse("El rechazo a sido aceptado");
        return response;
    }

    /*
    @GetMapping("/asign/request")
    public ResponseDto<List<RequestSearchDto>> asignClassroom (@RequestHeader Map<String, String> headers, @RequestBody) throws Exception {
        ResponseDto response = new ResponseDto<>();
        String token = authBl.getTokenFromHeader(headers);
        int id = authBl.getUserIdFromToken(token);
        if(!authBl.validateToken(token)){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        Long idEnv = 10L;
        response.setCode("0000");
        response.setResponse(requestBl.asignEnvironment(idEnv));
        return response;
    }

     */







}
