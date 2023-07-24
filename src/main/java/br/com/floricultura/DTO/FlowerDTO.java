package br.com.floricultura.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FlowerDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String Name;
    @NotNull
    @NotEmpty
    @NotBlank
    private String link;
}
