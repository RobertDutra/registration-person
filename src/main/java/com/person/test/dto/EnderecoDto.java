package com.person.test.dto;

import com.person.test.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EnderecoDto {

    @NotBlank
    private String lagradouro;

    @NotBlank
    private String cep;

    @NotNull
    private Integer numero;

    @NotBlank
    private String cidade;

    @NotNull
    private Pessoa pessoaId;

    @NotNull
    private Boolean enderecoPrincipal;
}
