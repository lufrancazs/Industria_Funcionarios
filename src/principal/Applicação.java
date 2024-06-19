package principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import calculadora.Calculadora;
import entidades.Funcionarios;

public class Applicação {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);

		List<Funcionarios> funcionarios = new ArrayList<>();

		System.out.println("Entre com o documento desejado: ");
		String path = sc.nextLine();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

			String line = bufferedReader.readLine();

			while (line != null) {

				String[] fields = line.split(",");

				LocalDate dataNascimento = LocalDate.parse(fields[1].trim());
				BigDecimal salario = new BigDecimal(fields[2].trim());

				Funcionarios funcionario = new Funcionarios(fields[0].trim(), dataNascimento, salario, fields[3]);
				funcionarios.add(funcionario);

				line = bufferedReader.readLine();

			}

			System.out.println("Lista Com Carga Inicial:");

			for (Funcionarios f : funcionarios) {
				System.out.println(f.getNome() + ", " + f.getDataNascimento().format(dtf) + ", "
						+ NumberFormat.getCurrencyInstance().format(f.getSalario()) + ", " + f.getFuncao());
			}

			System.out.println();
			System.out.println("-----------------------------------------------------------");

			System.out.println("Lista Sem funcionario João:");

			funcionarios.removeIf(f -> "João".equals(f.getNome()));

			for (Funcionarios f : funcionarios) {
				System.out.println(f.getNome() + ", " + f.getDataNascimento().format(dtf) + ", "
						+ NumberFormat.getCurrencyInstance().format(f.getSalario()) + ", " + f.getFuncao());
			}

			System.out.println();
			System.out.println("-----------------------------------------------------------");

			System.out.println("Lista com Aumento de 10% para todos os funcionarios:");

			BigDecimal aumentoSalario = new BigDecimal("10");

			for (Funcionarios f : funcionarios) {

				Calculadora.aumentoSalario(f, aumentoSalario);

				System.out.println(f.getNome() + ", " + f.getDataNascimento().format(dtf) + ", "
						+ NumberFormat.getCurrencyInstance().format(f.getSalario()) + ", " + f.getFuncao());
			}

			System.out.println();
			System.out.println("-----------------------------------------------------------");

			System.out.println("Lista com Map, sendo a chave a função e o valor a lista de funcionarios:");

			for (Funcionarios f : funcionarios) {
				Map<String, List<Funcionarios>> funcionariosMap = new HashMap<>();
				funcionariosMap.computeIfAbsent(f.getFuncao(), c -> new ArrayList<>()).add(f);

				System.out.println(f.getNome() + funcionariosMap.keySet());
			}

			System.out.println();
			System.out.println("-----------------------------------------------------------");

			System.out.println("Lista de funcionarios que fazem aniversario nos meses 10 e 12:");

			for (Funcionarios f : funcionarios) {

				int mesNascimento = f.getDataNascimento().getMonthValue();

				if (mesNascimento == 10 || mesNascimento == 12) {
					System.out.println(f.getNome() + ", " + f.getDataNascimento().format(dtf) + ", "
							+ NumberFormat.getCurrencyInstance().format(f.getSalario()) + ", " + f.getFuncao());

				}
			}

			System.out.println();
			System.out.println("-----------------------------------------------------------");

			System.out.println("Funcionario mais velho:");
			
			Funcionarios funcionarioMaisVelho = null;
			
			for(Funcionarios f : funcionarios) {
				if(funcionarioMaisVelho == null || f.getIdade() > funcionarioMaisVelho.getIdade()) {
					funcionarioMaisVelho = f;
				}
			}
			
			if(funcionarioMaisVelho != null) {
				System.out.println(funcionarioMaisVelho.getNome() + ", " + funcionarioMaisVelho.getIdade());
			}
			
			else {
				System.out.println("Não encontrado funcionario na lista");
			}
			
			System.out.println();
			System.out.println("-----------------------------------------------------------");

			System.out.println("Lista por ordem Alfabetica:");
			
			Collections.sort(funcionarios, (f1, f2) -> f1.getNome().compareTo(f2.getNome()));
			
			for(Funcionarios f : funcionarios) {
				System.out.println(f.getNome());
			}
			
			System.out.println();
			System.out.println("-----------------------------------------------------------");

			System.out.println("Soma dos Salarios:");
			
			System.out.println(NumberFormat.getCurrencyInstance().format(Calculadora.somaSalarios(funcionarios)));
			
			System.out.println();
			System.out.println("-----------------------------------------------------------");

			System.out.println("Salarios até R$1.212,00:");
			
			for(Funcionarios f : funcionarios) {
				
				BigDecimal salariosMinimos = Calculadora.calcularSalarioMinimos(f);
				System.out.println(f.getNome() + " - Recebe: " + String.format("%.2f", salariosMinimos) + " salários Mínimos");
				
			}
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();
	}

}
