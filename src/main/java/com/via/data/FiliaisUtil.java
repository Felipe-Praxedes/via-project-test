package com.via.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.via.model.Filial;
import com.via.model.Filial.TipoFilial;

public class FiliaisUtil {
	
	private static final Logger log = LoggerFactory.getLogger(FiliaisUtil.class);

	private static List<Filial> exemplosFilial() {
	    List<Filial> filiais = new ArrayList<>();
	    Filial filial1 = new Filial();
	    filial1.setNome("1200");
	    filial1.setCnpj(12345678901234L);
	    filial1.setCidade("São Paulo");
	    filial1.setUf("SP");
	    filial1.setTipo(TipoFilial.CD);
	    filial1.setAtivo(true);
	    filial1.setDataCadastro(LocalDateTime.now().minusDays(10));
	    filial1.setUltimaAtualizacao(LocalDateTime.now().minusDays(1));
	    filiais.add(filial1);

	    Filial filial2 = new Filial();
	    filial2.setNome("1040");
	    filial2.setCnpj(98765432101234L);
	    filial2.setUf("RJ");
	    filial2.setTipo(TipoFilial.LOJA);
	    filial2.setAtivo(true);
	    filial2.setDataCadastro(LocalDateTime.now().minusDays(20));
	    filial2.setUltimaAtualizacao(LocalDateTime.now().minusDays(3));
	    filiais.add(filial2);
	    
	    Filial filial4 = new Filial();
	    filial4.setNome("2300");
	    filial4.setCnpj(45678901231234L);
	    filial4.setCidade("Rio de Janeiro");
	    filial4.setUf("RJ");
	    filial4.setTipo(TipoFilial.LOJA);
	    filial4.setAtivo(true);
	    filial4.setDataCadastro(LocalDateTime.now().minusDays(5));
	    filial4.setUltimaAtualizacao(LocalDateTime.now().minusDays(2));
	    filiais.add(filial4);

	    Filial filial3 = new Filial();
	    filial3.setNome("5450");
	    filial3.setCnpj(56789012341234L);
	    filial3.setCidade("Belo Horizonte");
	    filial3.setUf("MG");
	    filial3.setTipo(TipoFilial.ONLINE);
	    filial3.setAtivo(false);
	    filial3.setDataCadastro(LocalDateTime.now().minusDays(30));
	    filiais.add(filial3);
	    
	    Filial filial5 = new Filial();
	    filial5.setNome("1700");
	    filial5.setCnpj(23456789011234L);
	    filial5.setCidade("Curitiba");
	    filial5.setUf("PR");
	    filial5.setTipo(TipoFilial.CD);
	    filial5.setAtivo(false);
	    filial5.setDataCadastro(LocalDateTime.now().minusDays(15));
	    filial5.setUltimaAtualizacao(LocalDateTime.now().minusDays(1));
	    filiais.add(filial5);

	    Filial filial6 = new Filial();
	    filial6.setNome("3120");
	    filial6.setCnpj(89012345671234L);
	    filial6.setCidade("Fortaleza");
	    filial6.setUf("CE");
	    filial6.setTipo(TipoFilial.ONLINE);
	    filial6.setAtivo(true);
	    filial6.setDataCadastro(LocalDateTime.now().minusDays(25));
	    filiais.add(filial6);

	    return filiais;
	}
	
	public static void postFiliais() {
	    RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/filiais";

	    List<Filial> filiais = exemplosFilial();
	    for (Filial filial : filiais) {
	        HttpEntity<Filial> request = new HttpEntity<>(filial);
	        ResponseEntity<Filial> response = restTemplate.postForEntity(url, request, Filial.class);
	        if (response.getStatusCode() == HttpStatus.OK) {
	        	log.info("Filial {} criada com sucesso!", filial.getNome());
	        } else {
	        	log.error("Erro ao criar filial {}", filial.getNome());
	        }
	    }
	}

}
