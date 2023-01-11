package com.person.test;

import com.person.test.controller.EnderecoController;
import com.person.test.dto.EnderecoDto;
import com.person.test.dto.PessoaDto;
import com.person.test.entity.Endereco;
import com.person.test.entity.Pessoa;
import com.person.test.repository.EnderecoRepository;
import com.person.test.repository.PessoaRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class EnderecoControllerTest {

    @Autowired
    EnderecoController controller;

    @MockBean
    EnderecoRepository enderecoRepository;

    @MockBean
    PessoaRepository pessoaRepository;

    @Test
    @DisplayName("Deve salvar edenreço com sucesso")
    void salvarComSucesso() {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Robert");
        pessoa.setDataNascimento(new Date());
        pessoa.setEnderecosList(Lists.emptyList());

        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setLagradouro("Luciano Manoel De Souza");
        enderecoDto.setCep("88134-485");
        enderecoDto.setNumero(188);
        enderecoDto.setCidade("Palhoça");
        enderecoDto.setEnderecoPrincipal(true);
        enderecoDto.setPessoaId(pessoa);


        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLagradouro("Luciano Manoel De Souza");
        endereco.setCep("88134-485");
        endereco.setNumero(188);
        endereco.setCidade("Palhoça");
        endereco.setEnderecoPrincipal(true);

        Mockito.when(pessoaRepository.findById(any())).thenReturn(Optional.of(pessoa));
        Mockito.when(enderecoRepository.save(any())).thenReturn(endereco);

        this.controller.salvar(enderecoDto);

    }

    @Test
    @DisplayName("Deve listar uma lista de endereços com sucesso")
    void listarEnderecos() {

        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLagradouro("Luciano Manoel De Souza");
        endereco.setCep("88134-485");
        endereco.setNumero(188);
        endereco.setCidade("Palhoça");
        endereco.setEnderecoPrincipal(true);

        Mockito.when(enderecoRepository.findAll()).thenReturn(List.of(endereco));

        this.controller.enderecoList();

    }

    @Test
    @DisplayName("Deve buscar endereço com sucesso")
    void buscarComSucesso() {

        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLagradouro("Luciano Manoel De Souza");
        endereco.setCep("88134-485");
        endereco.setNumero(188);
        endereco.setCidade("Palhoça");
        endereco.setEnderecoPrincipal(true);

        Mockito.when(enderecoRepository.findById(any())).thenReturn(Optional.of(endereco));
        this.controller.buscar(1L);
    }

    @Test
    @DisplayName("Deve retornar error ao buscar um endereço")
    void buscarError() {
        Mockito.when(enderecoRepository.findById(eq(1L))).thenReturn(Optional.empty());
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
        pessoa.setEnderecosList(Lists.emptyList());

        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLagradouro("Luciano Manoel De Souza");
        endereco.setCep("88134-485");
        endereco.setNumero(188);
        endereco.setCidade("Palhoça");
        endereco.setEnderecoPrincipal(true);

        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setLagradouro("Luciano Manoel De Souza");
        enderecoDto.setCep("88134-485");
        enderecoDto.setNumero(188);
        enderecoDto.setCidade("Palhoça");
        enderecoDto.setEnderecoPrincipal(true);
        enderecoDto.setPessoaId(pessoa);

        Endereco endereco2 = new Endereco();
        endereco2.setId(1L);
        endereco2.setLagradouro("Luciano Manoel De Souza");
        endereco2.setCep("88134-485");
        endereco2.setNumero(188);
        endereco2.setCidade("Palhoça");
        endereco2.setEnderecoPrincipal(true);


        Mockito.when(enderecoRepository.findById(any())).thenReturn(Optional.of(endereco));
        Mockito.when(enderecoRepository.save(any())).thenReturn(endereco2);
        Mockito.when(pessoaRepository.findById(any())).thenReturn(Optional.of(pessoa));
        EnderecoDto enderecoAtualizado = this.controller.atualizar(1L, enderecoDto);

        assertEquals(enderecoAtualizado.getNumero(), enderecoDto.getNumero());

    }

    @Test
    @DisplayName("Deve deletar com sucesso um endereço")
    void deletarComSucesso() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Robert");
        pessoa.setDataNascimento(new Date());
        pessoa.setEnderecosList(Lists.emptyList());

        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setLagradouro("Luciano Manoel De Souza");
        enderecoDto.setCep("88134-485");
        enderecoDto.setNumero(188);
        enderecoDto.setCidade("Palhoça");
        enderecoDto.setEnderecoPrincipal(true);
        enderecoDto.setPessoaId(pessoa);

        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLagradouro("Luciano Manoel De Souza");
        endereco.setCep("88134-485");
        endereco.setNumero(188);
        endereco.setCidade("Palhoça");
        endereco.setEnderecoPrincipal(true);


        Mockito.when(enderecoRepository.findById(any())).thenReturn(Optional.of(endereco));
        Mockito.doNothing().when(enderecoRepository).delete(any());

        this.controller.deletar(endereco.getId());
    }

    @Test
    @DisplayName("Deve retornar error ao tentar deletar um endereço")
    void deletarComError() {
        Mockito.when(enderecoRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
                this.controller.deletar(1L)
        );
    }
}
