package com.person.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityScan("com.person.test")
@Table(name = "endereco")


public class Endereco implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "lagradouro")
    private String lagradouro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "cidade")
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

}
