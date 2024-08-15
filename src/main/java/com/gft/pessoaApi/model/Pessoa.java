package com.gft.pessoaApi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    private String cep;
    private String logradouro;
    private String bairro;
    private String uf;
    private String cidade;

    @OneToMany(mappedBy = "pessoaId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Boleto> boletos;
}