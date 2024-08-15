package com.gft.pessoaApi;


import com.gft.pessoaApi.model.Boleto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "boleto-service", url = "http://localhost:8181/boletos")
public interface BoletoClient {
    @GetMapping("/pessoas/{id}/boletos")
    List<Boleto> buscarBoletosPorPessoaId(@PathVariable("id") Long pessoaId);


}