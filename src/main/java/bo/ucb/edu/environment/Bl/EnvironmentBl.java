package bo.ucb.edu.environment.Bl;

import bo.ucb.edu.environment.Dao.EnvironmentDao;
import bo.ucb.edu.environment.Dto.EnvironmentDto;
import bo.ucb.edu.environment.Entity.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnvironmentBl {
    @Autowired
    EnvironmentDao environmentDao;

    public List<EnvironmentDto> getAllEnvironments(){
        List<Environment> environments = environmentDao.findAll();
        List<EnvironmentDto> environmentDtos  = new ArrayList<>();
        environments.forEach (environment -> {
            EnvironmentDto environmentDto = new EnvironmentDto();
            environmentDto.setId(Math.toIntExact(environment.getEnvironmentId()));
            environmentDto.setType(environment.getType());
            environmentDto.setState(environment.getEnvState());
            environmentDtos.add(environmentDto);
        });
        return environmentDtos;
    }









}
