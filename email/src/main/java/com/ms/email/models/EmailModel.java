package com.ms.email.models;

import com.ms.email.enums.StatusEmail;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_email")
public class EmailModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailId;

    private String ownerRef;                    //ref do propriotario que ta mandando esse email, *este campo recebe o id do usuario que vai receber o email*
    private String emailFrom;                   //de quem ta mandando o email
    private String emailTo;                     //para quem o email vai ser enviado
    private String subject;                     //titulo do email

    @Column(columnDefinition = "TEXT")          //pro texto poder ser longo
    private String text;                        //texto/corpo do email

    private LocalDateTime sendDateEmail;        //data de envio
    private StatusEmail statusEmail;            //sent(enviado) ou error(nao enviou)

}
