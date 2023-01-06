package com.person.test.controller;

import com.person.test.dto.PessoaDto;
import com.person.test.entity.Pessoa;
import com.person.test.service.PessoaService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaDto salvar(@RequestBody PessoaDto pessoaDto){
        return this.pessoaService.salvar(pessoaDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaDto> listar(){
        return this.pessoaService.lista();
    }
}
