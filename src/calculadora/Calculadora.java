package calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import entidades.Funcionarios;

public class Calculadora {
	
	public static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212");

	public static void aumentoSalario(Funcionarios funcionario, BigDecimal percentual) {

		BigDecimal aumento = funcionario.getSalario().multiply(percentual).divide(new BigDecimal("100"));
		BigDecimal novoSalario = funcionario.getSalario().add(aumento).setScale(2, RoundingMode.HALF_UP);
		funcionario.setSalario(novoSalario);
	}
	
	public static BigDecimal somaSalarios(List<Funcionarios> funcionarios) {
		BigDecimal total = BigDecimal.ZERO;
		for(Funcionarios f : funcionarios) {
			total = total.add(f.getSalario());
		}
		return total;
	}
	
	public static BigDecimal calcularSalarioMinimos(Funcionarios funcionario) {
		return funcionario.getSalario().divide(SALARIO_MINIMO,2, RoundingMode.HALF_UP);
	}
}
