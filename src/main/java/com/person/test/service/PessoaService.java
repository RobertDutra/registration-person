package com.person.test.service;

import com.person.test.dto.PessoaDto;
import com.person.test.entity.Pessoa;
import com.person.test.interfaces.PessoaInterface;
import com.person.test.repository.PessoaRepository;
import com.person.test.utils.PessoaMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService implements PessoaInterface {

    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    public PessoaDto salvar(PessoaDto pessoaDto) {
        this.pessoaRepository.save(PessoaMapper.dtoToEntity(pessoaDto));
        return pessoaDto;
    }

    @Override
    public List<PessoaDto> lista() {
        return this.pessoaRepository.findAll().stream().map(PessoaMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Pessoa buscar(Long id) {
        return this.pessoaRepository.findById(id).orElseThrow(() ->{
            throw  new EntityNotFoundException("Pessoa com id + " + id + " não encontrado");
        });

    }

    @Override
    public PessoaDto atualizar(Long id, PessoaDto pessoaDto) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
