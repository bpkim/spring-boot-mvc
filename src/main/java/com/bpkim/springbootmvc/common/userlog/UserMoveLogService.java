package com.bpkim.springbootmvc.common.userlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMoveLogService {

    @Autowired
    private UserMoveLogRepository userMoveLogRepository;

    public UserMoveLog createUserMoveLog(UserMoveLog userMoveLog){
        return userMoveLogRepository.save(userMoveLog);
    }
}
