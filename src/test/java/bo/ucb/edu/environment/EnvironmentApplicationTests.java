package bo.ucb.edu.environment;

import bo.ucb.edu.environment.Bl.CarrerDepartmentBl;
import bo.ucb.edu.environment.Entity.CarrerDepartment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EnvironmentApplicationTests {


	@Test
	void contextLoads() {
	}

//	@Test
//	public void createCarrerDepartment() {
//		CarrerDepartment carrerDepartment = new CarrerDepartment();
//		carrerDepartment.setId((long) 1234);
//		carrerDepartment.setName("Administración");
//		carrerDepartment.setCode("ADM");
//		carrerDepartment.setStatus(true);
//		carrerDepartment.setTxDate(new Date());
//		carrerDepartment.setTxHost("localhost");
//		carrerDepartment.setTxUser("SebastianBelmonte");
//		CarrerDepartment newCarrerDepartment  = carrerDepartmentBl.save(carrerDepartment);
//		assertNotNull(newCarrerDepartment, "No se pudo crear la carrera");
//	}


}
