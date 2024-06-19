package entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionarios extends Pessoas{
	
	private BigDecimal salario;
	private String funcao;
	
	
	public Funcionarios(String name, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(name, dataNascimento);
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
	
	
	
	

}
