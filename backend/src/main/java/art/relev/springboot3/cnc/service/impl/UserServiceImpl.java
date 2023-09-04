package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.UserDao;
import art.relev.springboot3.cnc.exception.CNCException;
import art.relev.springboot3.cnc.model.Resource;
import art.relev.springboot3.cnc.model.User;
import art.relev.springboot3.cnc.param.UserParam;
import art.relev.springboot3.cnc.response.ResultMessage;
import art.relev.springboot3.cnc.service.JWTService;
import art.relev.springboot3.cnc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate redisTemplate;

    private String generateToken(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("userResource", user.getResource());
        String token = jwtService.encode(map);
        // TODO: 过期时间不准确
        redisTemplate.opsForValue().set("token::" + token, user.getResource().getResourceName(), 1, TimeUnit.DAYS);
        return token;
    }

    @Override
    public String register(UserParam.Register param) {
        User user = userDao.getUserByName(param.getName());
        if (user != null) {
            throw new CNCException(ResultMessage.USER_EXIST_ERROR);
        }
        Resource resource = Resource.builder().resourceName("user").build();
        user = User.builder().resource(resource).name(param.getName()).password(passwordEncoder.encode(param.getPassword())).build();
        userDao.save(user);
        resource.getOwnerIdSet().add(resource.getId());
        userDao.save(user);
        return generateToken(user);
    }

    @Override
    public String login(UserParam.Login param) {
        User user = userDao.getUserByName(param.getName());
        if (user == null) {
            throw new CNCException(ResultMessage.USER_NOT_EXIST_ERROR);
        }
        if (!passwordEncoder.matches(param.getPassword(), user.getPassword())) {
            throw new CNCException(ResultMessage.USER_PASSWORD_ERROR);
        }
        return generateToken(user);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
    }
}
