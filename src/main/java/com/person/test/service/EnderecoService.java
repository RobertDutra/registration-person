package com.person.test.service;

import com.person.test.dto.EnderecoDto;
import com.person.test.entity.Endereco;
import com.person.test.interfaces.EnderecoInterface;
import com.person.test.repository.EnderecoRepository;
import com.person.test.utils.EnderecoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService implements EnderecoInterface {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Override
    public EnderecoDto salvar(EnderecoDto enderecoDto) {
        this.enderecoRepository.save(EnderecoMapper.dtoToEntity(enderecoDto));
        return enderecoDto;
    }

    @Override
    public List<EnderecoDto> lista() {
        return this.enderecoRepository.findAll().stream().map(EnderecoMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Endereco buscar(Long id) {
        return this.enderecoRepository.findById(id).orElseThrow(() ->{
            throw  new EntityNotFoundException("Endereço com id " + id + " não encontrado");
        });
    }

    @Override
    public EnderecoDto atualizar(Long id, EnderecoDto enderecoDto) {
        Optional<Endereco> endereco = this.enderecoRepository.findById(id);
        if (endereco.isPresent()){
            Endereco endereco1 = EnderecoMapper.dtoToEntity(enderecoDto);
            BeanUtils.copyProperties(endereco1, endereco.get(), "id");
            this.enderecoRepository.save(endereco.get());
            return enderecoDto;
        }
        else{
            throw new EntityNotFoundException("Produto com id " + id + " não encontrado!");
        }
    }

    @Override
    public void deletar(Long id) {
        Optional<Endereco> endereco = this.enderecoRepository.findById(id);
        if(endereco.isPresent()){
            this.enderecoRepository.delete(endereco.get());
        }
        else {
            throw new EntityNotFoundException("Endereço com id " + id + " não encontrado!");
        }
    }
}
