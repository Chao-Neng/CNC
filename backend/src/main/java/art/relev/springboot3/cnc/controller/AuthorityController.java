package art.relev.springboot3.cnc.controller;

import art.relev.springboot3.cnc.service.AuthorityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authority")
@Tag(name = "authority", description = "权限相关接口")
@RequiredArgsConstructor
public class AuthorityController {
    private final AuthorityService authorityService;
}
