import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Exceptions.VerificaTransicoesAFDException;

/*Objetivo: receber os cinco parametros do AFD, converter-los para conseguir realizar operações, apresentar os
 * dados recebidos de modo organizado, verificar strings
*/	

public class AFD extends Tupla{
	protected List<List<String>> matrizTransicao;
	
	public AFD(String estados, String alfabeto, String transicao, String estadoInicial, String estadosFinais) {
		super(estados, alfabeto, estadoInicial, estadosFinais);
		separarTransicoes(transicao);
		
		try {
			VerificaTransicoesAFDException.verificaEstadoFinal(conjEstados, conjAlfabeto, matrizTransicao);
		} catch (VerificaTransicoesAFDException e) {
			e.printStackTrace();
		}
		
		System.out.println(toString());
	}
	
	//separa as transições através do método split e armazena-as em uma lista de listas
	private void separarTransicoes(String s) {
		String str[] = s.split(";"), str2[];
		matrizTransicao = new ArrayList<List<String>>();
		
		for(int i = 0; i < str.length; i++) {
			str2 = str[i].split(",");
			matrizTransicao.add(Arrays.asList(str2));
		}
	}
	
	//recebe uma string e verifica se o autômato aceita ou rejeita a string
	public void verificaString(String s) {
		String estadoAtual = estadoInicial;
		boolean aceita = false;
		
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < matrizTransicao.size(); j++) {
				if(estadoAtual.equals(matrizTransicao.get(j).get(0)) && s.substring(i, i + 1).equals(matrizTransicao.get(j).get(1))) {
					estadoAtual = matrizTransicao.get(j).get(2);
					break;
				}
			}
		}
		
		for (int i = 0; i < conjEstadosFinais.size(); i++) {
			if(estadoAtual.equals(conjEstadosFinais.get(i))) {
				aceita = true;
			}
		}
		
		if(aceita == true) {
			System.out.println("A aceita " + s);
		}else {
			System.out.println("A rejeita " + s);
		}
	}
	
	//apresenta as transições organizadamente
	public String toString() {
		return super.toString() + "\nTransições: " + matrizTransicao;
	}
}
