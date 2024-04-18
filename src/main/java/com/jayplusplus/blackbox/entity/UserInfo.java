package com.jayplusplus.blackbox.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * The type User info.
 */
@Entity
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    private String roles;
    @NotBlank
    private String password;
}
