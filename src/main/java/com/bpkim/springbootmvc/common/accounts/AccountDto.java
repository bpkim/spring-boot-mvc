package com.bpkim.springbootmvc.common.accounts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private String email;
    private String password;
    private String roles;
}
