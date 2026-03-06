package com.redjungi.hexagonal.adapter.web.auth.controller;

import com.redjungi.hexagonal.adapter.web.auth.dto.SignUpDto;
import com.redjungi.hexagonal.application.user.data.SignUpData;
import com.redjungi.hexagonal.application.user.port.in.SignUpUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping("sign-up")
    public ResponseEntity<SignUpDto.Response> signUp(
            @Valid @RequestBody SignUpDto.Request request
    ){
        SignUpData.Command command = request.toCommand();
        SignUpData.Result result = signUpUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpDto.Response(result.message()));
    }
}
