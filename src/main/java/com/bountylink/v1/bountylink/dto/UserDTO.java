package com.bountylink.v1.bountylink.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class UserDTO {


    private String username;

    private String email;

    private String password;

    private boolean flag;

    public UserDTO(String username, String email, String password, boolean flag) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.flag = flag;
    }
}
