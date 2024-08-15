package com.gft.pessoaApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boleto {

    @Id
    private Long id;

    private Long pessoaId;

    private BigDecimal valorDocumento;

    private LocalDate dataVencimento;

    private BigDecimal valorPago;

    private LocalDate dataPagamento;

    private StatusBoleto status;

    public enum StatusBoleto {
        PENDENTE,
        PAGO,
        VENCIDO
    }
}
