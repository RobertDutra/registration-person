package com.person.test.service;

import com.person.test.dto.EnderecoDto;
import com.person.test.entity.Endereco;
import com.person.test.entity.Pessoa;
import com.person.test.interfaces.EnderecoInterface;
import com.person.test.repository.EnderecoRepository;
import com.person.test.repository.PessoaRepository;
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

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PessoaService pessoaService;

    @Override
    public EnderecoDto salvar(EnderecoDto enderecoDto) {
        Optional<Pessoa> pessoa  = this.pessoaRepository.findById(enderecoDto.getPessoaId().getId());
        if(pessoa.isPresent()){
            if(enderecoDto.getEnderecoPrincipal()){
                for (Endereco enderecos: pessoa.get().getEnderecosList()) {
                    enderecos.setEnderecoPrincipal(false);
                }
                this.enderecoRepository.save(EnderecoMapper.dtoToEntity(enderecoDto));
            }
        }
        else {
            throw  new EntityNotFoundException("Id de pessoa não encotrado!");
        }
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
        Optional<Pessoa> pessoa  = this.pessoaRepository.findById(enderecoDto.getPessoaId().getId());

        if (endereco.isPresent()){
            if(pessoa.isPresent()){
                if(enderecoDto.getEnderecoPrincipal()){
                    for (Endereco enderecos: pessoa.get().getEnderecosList()) {
                        enderecos.setEnderecoPrincipal(false);
                    }
                }
            }
            else {
                throw new EntityNotFoundException("Pessoa com id "+ enderecoDto.getPessoaId().getId() + " não encontrado!" );
            }
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
