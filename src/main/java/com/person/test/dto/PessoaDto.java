package com.person.test.dto;

import com.person.test.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PessoaDto {

    @NotBlank
    private String nome;

    @NotNull
    private Date dataNascimento;

    private List<Endereco> enderecosList;

}
