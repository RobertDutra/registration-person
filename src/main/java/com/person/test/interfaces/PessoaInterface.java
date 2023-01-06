package com.person.test.interfaces;

import com.person.test.dto.PessoaDto;
import com.person.test.entity.Pessoa;

import java.util.List;

public interface PessoaInterface {

    PessoaDto salvar(PessoaDto pessoaDto);

    List<PessoaDto> lista();

    Pessoa buscar(Long id);

    PessoaDto atualizar(Long id, PessoaDto pessoaDto);

    void deletar(Long id);
}
