package com.person.test.utils;

import com.person.test.dto.PessoaDto;
import com.person.test.entity.Pessoa;
import org.springframework.beans.BeanUtils;

public class PessoaMapper {

    public static Pessoa dtoToEntity(PessoaDto pessoaDto){
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return pessoa;
    }

    public static PessoaDto entityToDto(Pessoa pessoa){
        var pessoaDto = new PessoaDto();
        BeanUtils.copyProperties(pessoa, pessoaDto);
        return pessoaDto;
    }
}
