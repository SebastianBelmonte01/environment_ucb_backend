package bo.ucb.edu.environment.Bl;


import bo.ucb.edu.environment.Dao.UserRepository;
import bo.ucb.edu.environment.Dao.VerificationCodeRepository;
import bo.ucb.edu.environment.Entity.User;
import bo.ucb.edu.environment.Entity.VerificationCode;
import bo.ucb.edu.environment.Utils.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class MailBl {

    @Autowired
    VerificationCodeRepository verificationCodeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private Hash hash;

    Logger log = Logger.getLogger("bo.ucb.edu.environment.Bl.MailBl");

    public void saveVerificationCode(Long code){
        VerificationCode verificationCode = new VerificationCode();
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        verificationCode.setVcUuid(randomUUIDString);
        verificationCode.setCode(code);
        verificationCode.setUserId(1L);
        verificationCode.setStatus(true);
        verificationCodeRepository.save(verificationCode);
    }

    public boolean verifyCode(Long code){
        VerificationCode verificationCode = verificationCodeRepository.findVerificationCodeByCode(code.toString());
        log.info(verificationCode.toString());
        if(verificationCode != null){
            log.info("Code verified");
            return true;
        }else{
            return false;
        }
    }

    public void updatePassword(String mail, String password){
        log.info("Updating password");
        User user = userRepository.findByEmail(mail);
        user.setSecret(hash.hashString(password, mail));
        log.info("Password updated");
        userRepository.save(user);
    }




}
