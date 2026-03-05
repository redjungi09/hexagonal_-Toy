package com.redjungi.hexagonal.application.user.port.in;

import com.redjungi.hexagonal.application.user.data.SignUpData;

public interface SignUpUseCase {
    SignUpData.Result execute(SignUpData.Command command);
}
