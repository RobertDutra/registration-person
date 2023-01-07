package com.person.test.controller;

import com.person.test.dto.EnderecoDto;
import com.person.test.entity.Endereco;
import com.person.test.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("Serviço para cadastrar endereço.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoDto salvar(@RequestBody @Valid EnderecoDto enderecoDto){
        return this.enderecoService.salvar(enderecoDto);
    }
    @ApiOperation("Serviço para listar endereços cadastrados.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EnderecoDto> enderecoList(){
        return this.enderecoService.lista();
    }

    @ApiOperation("Serviço para buscar endereço, passando seu id.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Endereco buscar(@PathVariable Long id){
        return this.enderecoService.buscar(id);
    }

    @ApiOperation("Serviço para atualizar endereço, passando seu id.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EnderecoDto atualizar(@PathVariable Long id, @RequestBody @Valid EnderecoDto enderecoDto){
        return this.enderecoService.atualizar(id, enderecoDto);
    }
    @ApiOperation("Serviço para deletar endereço, passando seu id.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@PathVariable Long id){
        this.enderecoService.deletar(id);
    }
}
