package com.person.test.dto;

import com.person.test.entity.Endereco;
import com.person.test.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.interfaces.EdECKey;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PessoaDto {

    @NotBlank
    private String nome;

    @NotEmpty
    private Date dataNascimento;

    private List<Endereco> enderecosList;

}
