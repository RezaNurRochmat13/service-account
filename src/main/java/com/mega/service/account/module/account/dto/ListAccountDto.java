package com.mega.service.account.module.account.dto;

import com.mega.service.account.module.users.dto.SingleUserDto;
import com.mega.service.account.utils.entity.Auditing;
import lombok.Data;

@Data
public class ListAccountDto extends Auditing {
    private Long id;
    private String accountNumber;
    private Double balance;
    private SingleUserDto user;
}
