package com.person.test.interfaces;

import com.person.test.dto.EnderecoDto;
import com.person.test.dto.PessoaDto;
import com.person.test.entity.Endereco;
import com.person.test.entity.Pessoa;

import java.util.List;

public interface EnderecoInterface {
    EnderecoDto salvar(EnderecoDto enderecoDto);

    List<EnderecoDto> lista();

    Endereco buscar(Long id);

    EnderecoDto atualizar(Long id, EnderecoDto enderecoDto);

    void deletar(Long id);

}
