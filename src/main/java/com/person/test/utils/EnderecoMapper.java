package com.person.test.utils;

import com.person.test.dto.EnderecoDto;
import com.person.test.dto.PessoaDto;
import com.person.test.entity.Endereco;
import com.person.test.entity.Pessoa;
import org.springframework.beans.BeanUtils;

public class EnderecoMapper {

    public static Endereco dtoToEntity(EnderecoDto enderecoDto){
        var endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDto, endereco);
        return endereco;
    }

    public static EnderecoDto entityToDto(Endereco endereco){
        var enderecoDto = new EnderecoDto();
        BeanUtils.copyProperties(endereco, enderecoDto);
        return enderecoDto;
    }
}
