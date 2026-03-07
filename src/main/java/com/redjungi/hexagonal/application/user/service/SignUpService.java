package com.redjungi.hexagonal.application.user.service;

import com.redjungi.hexagonal.application.common.util.TokenGenerator;
import com.redjungi.hexagonal.application.mail.port.out.VerificationCodePort;
import com.redjungi.hexagonal.application.user.data.SignUpData;
import com.redjungi.hexagonal.application.user.port.in.SignUpUseCase;
import com.redjungi.hexagonal.application.user.port.out.PendingUserCachePort;
import com.redjungi.hexagonal.application.user.port.out.UserRepositoryPort;
import com.redjungi.hexagonal.domain.user.entity.User;
import com.redjungi.hexagonal.infrastructure.mail.SendVerificationCodeAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;
    private final PendingUserCachePort pendingUserCachePort;
    private final VerificationCodePort verificationCodePort;
    private final SendVerificationCodeAdapter sendVerificationCodeAdapter;
    private static final long PENDING_USER_EXPIRATION_MS = 30 * 60 * 1000L;
    private static final long VERIFICATION_CODE_EXPIRATION_MS = 30 * 60 * 1000L;

    @Override
    public String execute(SignUpData.Command command) {

        if(userRepositoryPort.existsByUsername(command.username())){
            throw new RuntimeException("이미 사용 중인 유저네임입니다.");
        }
        if(userRepositoryPort.existsByEmail(command.email())){
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        String encoded = passwordEncoder.encode(command.password());

        User user = User.create(
                command.username(),
                encoded,
                command.email()
        );

        String token = tokenGenerator.getToken();
        //토큰과함께 redis에 임시 저장
        pendingUserCachePort.saveUser(user, token, PENDING_USER_EXPIRATION_MS);

        String code = generatorCode();

        verificationCodePort.saveCode(token, code, VERIFICATION_CODE_EXPIRATION_MS);

        sendVerificationCodeAdapter.send(command.email(), code);


        return token;
    }

    private String generatorCode(){
        return String.format("%06d", new SecureRandom().nextInt(1000000));
    }
}
