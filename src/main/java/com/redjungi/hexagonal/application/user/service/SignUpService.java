package com.redjungi.hexagonal.application.user.service;

import com.redjungi.hexagonal.application.user.data.SignUpData;
import com.redjungi.hexagonal.application.user.port.in.SignUpUseCase;
import com.redjungi.hexagonal.application.user.port.out.UserRepositoryPort;
import com.redjungi.hexagonal.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public SignUpData.Result execute(SignUpData.Command command) {

        if(userRepositoryPort.existsByUsername(command.username())){
            throw new RuntimeException("이미 사용 중인 유저네임입니다.");
        }
        if(userRepositoryPort.existsByEmail(command.email())){
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        User user = User.create(
                command.username(),
                command.password(),
                command.email()
        );

        userRepositoryPort.save(user);

        return new SignUpData.Result("회원가입이 완료되었습니다.");
    }
}
