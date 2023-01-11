package com.person.test;

import com.person.test.controller.PessoaController;
import com.person.test.dto.PessoaDto;
import com.person.test.entity.Pessoa;
import com.person.test.repository.PessoaRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
class PessoaControllerTest {

    @Autowired
    PessoaController controller;

    @MockBean
    PessoaRepository repository;

    @Test
    @DisplayName("Deve salvar pessoa com sucesso")
    void salvarComSucesso() {

        PessoaDto pessoa = new PessoaDto();

        pessoa.setNome("Robert");
        pessoa.setDataNascimento(new Date());

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1L);
        pessoa1.setNome("Robert");
        pessoa1.setDataNascimento(new Date());

        Mockito.when(repository.save(any())).thenReturn(pessoa1);

        this.controller.salvar(pessoa);

    }

    @Test
    @DisplayName("Deve listar uma lista de pessoas com sucesso")
    void listarPessoas() {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Robert");
        pessoa.setDataNascimento(new Date());

        Mockito.when(repository.findAll()).thenReturn(List.of(pessoa));

        this.controller.listar();

    }

    @Test
    @DisplayName("Deve buscar pessoa com sucesso")
    void buscarComSucesso() {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Robert");
        pessoa.setDataNascimento(new Date());


        Mockito.when(repository.findById(any())).thenReturn(Optional.of(pessoa));
        this.controller.buscar(1L);
    }
    @Test
    @DisplayName("Deve retornar error ao buscar uma pessoa")
    void buscarError() {
        Mockito.when(repository.findById(eq(1L))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
            this.controller.buscar(1L)
        );
    }

    @Test
    @DisplayName("Deve atualizar pessoa com sucesso")
    void atualizarComSucesso() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Robert");
        pessoa.setDataNascimento(new Date());

        PessoaDto pessoa1 = new PessoaDto();
        pessoa1.setNome("Robert2");
        pessoa1.setDataNascimento(new Date());

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setId(1L);
        pessoa3.setNome("Robert2");
        pessoa3.setDataNascimento(new Date());
        pessoa3.setEnderecosList(Lists.emptyList());


        Mockito.when(repository.findById(any())).thenReturn(Optional.of(pessoa));
        Mockito.when(repository.save(any())).thenReturn(pessoa3);
        PessoaDto pessoaAtualizada = this.controller.atualizar(1L, pessoa1);

        assertEquals(pessoaAtualizada.getNome(), pessoa1.getNome());

    }

    @Test
    @DisplayName("Deve retornar error ao atualizar uma pessoa")
    void atualizarComError() {

        PessoaDto pessoa = new PessoaDto();
        pessoa.setNome("Robert");
        pessoa.setDataNascimento(new Date());

        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
                this.controller.atualizar(1L, pessoa)
        );

    }

    @Test
    @DisplayName("Deve deletar com sucesso uma pessoa")
    void deletarComSucesso() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Robert");
        pessoa.setDataNascimento(new Date());

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(pessoa));
        Mockito.doNothing().when(repository).delete(any());

        this.controller.deletar(pessoa.getId());
    }

    @Test
    @DisplayName("Deve retornar error ao tentar deletar uma pessoa")
    void deletarComError() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
                this.controller.deletar(1L)
        );
    }



}