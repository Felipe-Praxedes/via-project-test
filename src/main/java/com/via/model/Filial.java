package com.via.model;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Filial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private Long cnpj;

	@Column
	private String cidade;

	@Column(nullable = false)
	private String uf;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoFilial tipo;

	@Column(nullable = false)
	private boolean ativo;

	@Column(nullable = false)
	private LocalDateTime dataCadastro;

	@Column
	private LocalDateTime ultimaAtualizacao;
	
	@Transient
	private String cnpjFormatado;
	
	public enum TipoFilial {
		CD, LOJA, ONLINE;
		}
	
	private static final Pattern cnpjEntrada = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");

	public String getCnpjFormatado() {
		if (cnpj == null) {
			return null;
		}
		String cnpjString = cnpj.toString();
		if (cnpjString.length() != 14) {
			return cnpjString;
		}
		return cnpjEntrada.matcher(cnpjString).replaceAll("$1.$2.$3/$4-$5");
	}
	
}
