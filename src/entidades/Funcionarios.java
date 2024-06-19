package entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Funcionarios extends Pessoas{
	
	private BigDecimal salario;
	private String funcao;
	
	public Funcionarios() {
		
	}
	
	public Funcionarios(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		this.salario = salario;
		this.funcao = funcao;
	}


	public BigDecimal getSalario() {
		return salario;
	}


	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}


	public String getFuncao() {
		return funcao;
	}


	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	
	public Integer getIdade() {
		LocalDate hoje = LocalDate.now();
		return Period.between(getDataNascimento(), hoje).getYears();
	}

	

	
	
	
	

}
