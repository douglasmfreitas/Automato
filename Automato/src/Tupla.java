import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import Exceptions.VerificaEstadoInicialException;
import Exceptions.VerificaEstadosFinaisException;

/* Objetivo: definir em listas os estados, alfabeto e estado(s) final(is) do autômato finito, verificar se os dados
 * estão corretos e apresentar os parametros recebidos de forma organizada
 */

public class Tupla {
	protected List<String> conjEstados, conjAlfabeto, conjEstadosFinais;
	protected String estadoInicial;
	
	public Tupla(String estados, String alfabeto, String estadoInicial, String estadosFinais) {
		this.estadoInicial = estadoInicial;
		separarEstados(estados);
		separarAlfabeto(alfabeto);
		separarEstadosFinais(estadosFinais);
		
		
		try {
			VerificaEstadoInicialException.verificaEstadoInicial(conjEstados, estadoInicial);
			VerificaEstadosFinaisException.verificaEstadoFinal(conjEstados, conjEstadosFinais);
		}catch (VerificaEstadoInicialException e) {
			e.printStackTrace();
			System.out.println("Estado inicial " + estadoInicial + " não pertence a Q\n");
			
		}catch (VerificaEstadosFinaisException e) {
			e.printStackTrace();
			System.out.println("Estado(s) final(is) " + conjEstadosFinais + " não está contido em Q\n");
		}
	}
	
	//separa os estados e armazena os estados em uma lista
	private void separarEstados(String s) {
		String str[] = s.split(",");
		conjEstados = new ArrayList<String>();
		conjEstados = Arrays.asList(str);
	}
	
	//separa os símbolos do alfabeto e armazena-os numa lista
	private void separarAlfabeto(String s) {
		String str[] = s.split(",");
		conjAlfabeto = new ArrayList<String>();
		conjAlfabeto = Arrays.asList(str);
	}
	
	//separa os estados finais para armazenar em uma lista
	private void separarEstadosFinais(String s) {
		String str[] = s.split(",");
		conjEstadosFinais = new ArrayList<String>();
		conjEstadosFinais = Arrays.asList(str);
	}
	
	//apresenta os dados de forma organizada
	public String toString() {
		return "Estados: " + conjEstados + 
				"\nAlfabeto: " + conjAlfabeto +
				"\nEstado inicial: " + estadoInicial +
				"\nEstado(s) final(is): " + conjEstadosFinais;
	}
}
