package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Schedulers;

import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Logs.LogActivity;
import SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.Repositoriy.sessionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionExpirySchedule {
    @Autowired
    sessionRepo sessionRepository;

    // Scheduled to be run on each 5 minutes
    @Scheduled(fixedRate = 300000)
    @Transactional
    @LogActivity(methodDescription = "This method will schedule to expire database sessions in each 5 minutes")
    public void expireInactiveSessions() {
        //Get session token list that needs to be expired
        List<String> sessionTokenList = sessionRepository.getExpiredSessionList();
        if (!sessionTokenList.isEmpty()) {
            for (String token : sessionTokenList) {
                sessionRepository.scheduledSessionExpiration(token);
            }
        }
    }
}
