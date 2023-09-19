package bo.ucb.edu.environment.Api;

import bo.ucb.edu.environment.Bl.UserBl;
import bo.ucb.edu.environment.Dto.ResponseDto;
import bo.ucb.edu.environment.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/v1/")
public class UserApi {
    @Autowired
    private UserBl userBl;

    @PostMapping("/user")
    public ResponseDto<UserDto> createUser(@RequestBody UserDto userDto){
        ResponseDto<UserDto> response = new ResponseDto<>();
        response.setCode("0000");
        response.setResponse(userBl.createUser(userDto));
        return response;
    }



}
