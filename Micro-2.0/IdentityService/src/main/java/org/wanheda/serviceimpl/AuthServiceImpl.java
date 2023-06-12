package org.wanheda.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wanheda.entity.UserInfo;
import org.wanheda.repository.UserInfoRepository;
import org.wanheda.service.AuthService;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public List<UserInfo> getAll() {
        return userInfoRepository.findAll();
    }

    public String generateToken(String userName) {
        return jwtService.generateToken(userName);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
