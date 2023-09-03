package art.relev.springboot3.cnc.service.impl;

import art.relev.springboot3.cnc.dao.AuthorityDao;
import art.relev.springboot3.cnc.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityDao authorityDao;
}
