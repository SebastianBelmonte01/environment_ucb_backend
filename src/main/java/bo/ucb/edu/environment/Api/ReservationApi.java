package bo.ucb.edu.environment.Api;

import bo.ucb.edu.environment.Bl.AuthBl;
import bo.ucb.edu.environment.Bl.ReservationBl;
import bo.ucb.edu.environment.Dto.EntranceDto;
import bo.ucb.edu.environment.Dto.RequestDto;
import bo.ucb.edu.environment.Dto.ReservationDto;
import bo.ucb.edu.environment.Dto.ResponseDto;
import bo.ucb.edu.environment.Entity.Request;
import bo.ucb.edu.environment.Entity.Reservation;
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


    @PutMapping("/reservation/accept/{reservationId}")
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

    @GetMapping("/request/rejected")
    public ResponseDto<List<ReservationDto>> getRejectedRequests(@RequestHeader Map<String, String> headers) throws Exception {
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
        response.setResponse(reservationBl.getReservatonByState(id, "Rechazado"));
        return response;
    }


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

    @PostMapping("/reservation/reject/{reservationId}")
    public ResponseDto<RequestDto> rejectRequest (@RequestHeader Map<String, String> headers, @PathVariable("reservationId") Long reservationId, @RequestBody String reason) throws Exception {
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
        response.setResponse(reservationBl.rejectRequest(reservationId, reason));
        return response;
    }

    @PutMapping("/cancel/{idReservation}")
    public ResponseDto<RequestDto> cancelReservation(@RequestHeader("Authorization") String token, @PathVariable Long idReservation){
        ResponseDto response = new ResponseDto<>();
        if(!authBl.validateToken(token)){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        response.setCode("0000");
        response.setResponse(reservationBl.cancelReservation(idReservation));
        return response;
    }

    @PutMapping("/reservation/entrance")
    public ResponseDto<EntranceDto> updateReservation(@RequestHeader Map<String, String> headers, @RequestBody EntranceDto entranceDto) throws Exception {
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
        EntranceDto entranceDto1 = reservationBl.registerEntrance(entranceDto);
        if(entranceDto1 == null){
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Ambiente no corresponde");
            return response;
        }
        response.setResponse(reservationBl.registerEntrance(entranceDto));
        return response;
    }

    @GetMapping("/reservation/complete")
    public ResponseDto<List<ReservationDto>> getCompleteReservations(@RequestHeader Map<String, String> headers) throws Exception {
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
        response.setResponse(reservationBl.getReservatonByState(id, "Completado"));
        return response;
    }

    @PutMapping("/reservation/delete/{idReservation}")
    public ResponseDto<String> deleteReservation(@RequestHeader("Authorization") String token, @PathVariable Long idReservation) {
        ResponseDto response = new ResponseDto<>();
        if (!authBl.validateToken(token)) {
            response.setCode("0001");
            response.setResponse(null);
            response.setErrorMessage("Invalid credentials");
            return response;
        }
        response.setCode("0000");
        reservationBl. deleteReservation(idReservation);
        response.setResponse("Finalizado");
        return response;
    }


}
