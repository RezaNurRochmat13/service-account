package com.mega.service.account.module.users.dto;

import com.mega.service.account.utils.entity.Auditing;
import lombok.Data;

@Data
public class SingleUserDto extends Auditing {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
