package com.jayplusplus.blackbox.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Generic response.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse {
    private int code;
    private String message;

    /**
     * Instantiates a new Generic response.
     *
     * @param code    the code
     * @param message the message
     */
    public GenericResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
