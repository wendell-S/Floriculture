package br.com.floricultura.DTO;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class FlowerDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String Name;
    @NotNull
    @NotEmpty
    @NotBlank
    private String link;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
