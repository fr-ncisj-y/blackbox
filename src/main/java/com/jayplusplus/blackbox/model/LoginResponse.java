package com.jayplusplus.blackbox.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Login response.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    private int code;
    private String message;
    @JsonProperty("auth_token")
    private String authToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private long expiresIn;

    /**
     * Instantiates a new Login response.
     *
     * @param code      the code
     * @param message   the message
     * @param authToken the auth token
     * @param tokenType the token type
     * @param expiresIn the expires in
     */
    public LoginResponse(int code, String message, String authToken, String tokenType, long expiresIn) {
        this.code = code;
        this.message = message;
        this.authToken = authToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }
}
