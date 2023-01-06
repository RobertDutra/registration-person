package com.person.test.controller;

import com.person.test.dto.EnderecoDto;
import com.person.test.entity.Endereco;
import com.person.test.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/endereco")
@Validated
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoDto salvar(@RequestBody @Valid EnderecoDto enderecoDto){
        return this.enderecoService.salvar(enderecoDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EnderecoDto> enderecoList(){
        return this.enderecoService.lista();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Endereco buscar(@PathVariable Long id){
        return this.enderecoService.buscar(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EnderecoDto atualizar(@PathVariable Long id, @RequestBody @Valid EnderecoDto enderecoDto){
        return this.enderecoService.atualizar(id, enderecoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@PathVariable Long id){
        this.enderecoService.deletar(id);
    }
}
