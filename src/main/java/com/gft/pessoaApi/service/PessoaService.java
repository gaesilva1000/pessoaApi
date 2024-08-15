package com.gft.pessoaApi.service;


import com.gft.pessoaApi.BoletoClient;
import com.gft.pessoaApi.model.Boleto;
import com.gft.pessoaApi.model.Pessoa;
import com.gft.pessoaApi.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BoletoClient boletoClient;

    public Pessoa criarPessoa(Pessoa pessoa) {
        validarCpf(pessoa.getCpf());
        validarIdade(pessoa.getDataNascimento());
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> buscarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa listarPessoa(Long id) {
        Pessoa pessoa = buscarPessoaPorId(id);
        List<Boleto> boletos = boletoClient.buscarBoletosPorPessoaId(id);
        pessoa.setBoletos(boletos);

        return pessoa;
    }

    public Pessoa buscarPessoaPorId(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isEmpty()) {
            throw new IllegalStateException("Pessoa não encontrada");
        }

        return pessoaOptional.get();
    }



    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = buscarPessoaPorId(id);
        if (!pessoaExistente.getCpf().equals(pessoaAtualizada.getCpf())) {
            validarCpf(pessoaAtualizada.getCpf());
        }
        validarIdade(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setNome(pessoaAtualizada.getNome());

        return pessoaRepository.save(pessoaExistente);
    }

    public void excluirPessoa(Long id) {
        Pessoa pessoa = buscarPessoaPorId(id);

        List<Boleto> boletos = boletoClient.buscarBoletosPorPessoaId(id);

        if (boletos.isEmpty()) {
            throw new IllegalStateException("Não é possível excluir a pessoa sem boletos cadastrados.");
        }

        boolean temBoletoPendente = boletos.stream()
                .anyMatch(boleto -> boleto.getStatus() == Boleto.StatusBoleto.PENDENTE);

        if (temBoletoPendente) {
            throw new IllegalStateException("Não é possível excluir a pessoa com boletos pendentes.");
        }

        pessoaRepository.delete(pessoa);
    }

    private void validarCpf(String cpf) {
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter apenas números");
        }
        pessoaRepository.findByCpf(cpf).ifPresent(p -> {
            throw new IllegalArgumentException("CPF já cadastrado");
        });
    }

    private void validarIdade(LocalDate dataNascimento) {
        if (Period.between(dataNascimento, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("Pessoa deve ter 18 anos ou mais");
        }
    }
}
