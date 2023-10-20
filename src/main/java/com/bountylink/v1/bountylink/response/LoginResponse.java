package com.bountylink.v1.bountylink.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginResponse {
    String message;
    Boolean status;
}
