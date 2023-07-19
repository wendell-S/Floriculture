package com.floriculture.mysql;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Table(name = "users")
@Entity
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "Name")
    private String name;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "phone")
    private String phone;

    @NotNull
    @NotBlank
    @NotEmpty
    @Column(name = "password")
    private String password;


    public Users(@NotNull String name, @NotNull String email, @NotNull String phone, @NotNull String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Users(){

    }
}
