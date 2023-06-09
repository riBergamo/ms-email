package com.ms.email.dtos;

import com.ms.email.enums.StatusEmail;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class EmailDto {

    @NotBlank
    private String ownerRef;

    @NotBlank @Email
    private String emailFrom;

    @NotBlank @Email
    private String emailTo;

    @NotBlank
    private String subject;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String text;

}
