package com.redjungi.hexagonal.adapter.web.auth.dto;


import com.redjungi.hexagonal.application.user.data.SignUpData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignUpDto {

    public record Request(

            @Size(min = 3, max = 15)
            @NotBlank(message = "유저네임은 필수입니다.")
            String username,

            @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$",
                    message = "비밀번호는 영문+숫자+특수문자 8자 이상이어야합니다.")
            @NotBlank(message = "비밀번호는 필수입니다.")
            String password,

            @Email
            @NotBlank(message = "이메일은 필수입니다.")
            String email
    ){ public SignUpData.Command toCommand(){
         return new SignUpData.Command(
             username,
             password,
             email
         );
     }
    }

    public record Response(
            String message
    ){
    }
}
