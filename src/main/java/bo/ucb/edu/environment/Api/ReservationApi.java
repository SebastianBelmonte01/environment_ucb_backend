package bo.ucb.edu.environment.Api;

import bo.ucb.edu.environment.Bl.AuthBl;
import bo.ucb.edu.environment.Bl.ReservationBl;
import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.ReservationDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Transactional
public class ReservationApi {
    @Autowired
    private AuthBl authBl;
    @Autowired
    private ReservationBl reservationBl;


    @PutMapping("/reservation/{reservationId}")
    public ResponseDto<RequestDto> asignRequest(@RequestHeader Map<String, String> headers, @PathVariable("reservationId") Long reservationId) throws Exception {
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
        response.setResponse(reservationBl.acceptRequest(reservationId));
        return response;
    }

    @GetMapping("/reservation/pending")
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
        response.setResponse(reservationBl.getAllRequestsAdmin());
        return response;
    }

    /*
      @GetMapping("/request/accepted")
    public ResponseDto<List<ReservationDto>> getAcceptedRequests(@RequestHeader Map<String, String> headers) throws Exception {
        ResponseDto<List<ReservationDto>> response = new ResponseDto<>();
        String token = authBl.getTokenFromHeader(headers);
        int id = authBl.getUserIdFromToken(token);
        if(!authBl.validateToken(token)){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        response.setCode("0000");
        response.setResponse(reservationBl.getReservatonByState(id, "Aceptado"));
        return response;
    }
     */


    @GetMapping("/reservation/{reservationId}")
    public ResponseDto<RequestDto> getReservation(@RequestHeader Map<String, String> headers, @PathVariable("reservationId") Long reservationId) throws Exception {
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
        response.setResponse(reservationBl.getReservationById(reservationId));
        return response;
    }

}
