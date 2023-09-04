package art.relev.springboot3.cnc.service;

import art.relev.springboot3.cnc.param.UserParam;

public interface UserService {
    String login(UserParam.Login param);

    String register(UserParam.Register param);

    void logout(String token);
}
