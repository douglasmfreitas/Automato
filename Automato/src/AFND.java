import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Exceptions.VerificaTransicoesAFNDException;

/*Objetivo: receber os cinco parametros do AFND, converter-los para conseguir realizar operações, apresentar os
 * dados recebidos de modo organizado e verificar strings informando se é aceita ou rejeita
*/	

public class AFND extends Tupla{
	protected List<List<String>> matrizTransicao;
	
	public AFND(String estados, String alfabeto, String transicao, String estadoInicial, String estadosFinais) {
		super(estados, alfabeto, estadoInicial, estadosFinais);
		separarTransicoes(transicao);
		
		try {
			VerificaTransicoesAFNDException.verificaEstadoFinal(conjEstados, conjAlfabeto, matrizTransicao);
		} catch (VerificaTransicoesAFNDException e) {
			e.printStackTrace();
		}
		System.out.println(toString());
	}
	
	//separa as transições e armazena-as em uma lista de listas
	private void separarTransicoes(String s) {
		String str[] = s.split(";");
		String[] str2;
		matrizTransicao = new ArrayList<List<String>>();
		
		for(int i = 0; i < str.length; i++) {
			str2 = str[i].split(",");
			matrizTransicao.add(Arrays.asList(str2));
		}
	}
	
	public void verificaString(String s) {
		List<String> estadoAtual, novoEstado;
		int tamanho, count;
		boolean aceita = false;
		
		estadoAtual = new ArrayList<String>();
		estadoAtual.add(estadoInicial);
		System.out.println(estadoAtual + " - estado inicial");
		
		for (int i = 0; i < s.length(); i++) {
			tamanho = estadoAtual.size();
			for (int j = 0; j < tamanho; j++) {
				count = 0;
				for (int k = 0; k < matrizTransicao.size(); k++) {
					if(estadoAtual.get(j).equals(matrizTransicao.get(k).get(0)) && s.substring(i, i + 1).equals(matrizTransicao.get(k).get(1))) {
						estadoAtual.set(j, matrizTransicao.get(k).get(2));
						count++;
						for (int l = 3; l < matrizTransicao.get(k).size(); l++) {
							estadoAtual.add(matrizTransicao.get(k).get(l));
							count++;
						}
					}
				}
				if(count == 0) {
					estadoAtual.set(j, "morre");
				}
			}
			
			System.out.println(estadoAtual + " - transição com " + s.substring(i, i+1));
			
			for (int j = 0; j < estadoAtual.size(); j++) {
				novoEstado = eClose(estadoAtual.get(j));
				if(novoEstado != null) {
					estadoAtual.addAll(novoEstado);
				}
			}
		}
		
		for (int i = 0; i < conjEstadosFinais.size(); i++) {
			for (int j = 0; j < estadoAtual.size(); j++) {
				if(estadoAtual.get(j).equals(conjEstadosFinais.get(i))) {
					aceita = true;
				}
			}
		}
		
		if(aceita == true) {
			System.out.println("A aceita " + s + "\n");
		}else {
			System.out.println("A rejeita " + s + "\n");
		}
	}
	
	//verifica se o estado informado possui transações epsilon e retorna o(s) estado(s) na transição, se houver 
	private List<String> eClose(String estado) {
		for (int i = 0; i < matrizTransicao.size(); i++) {
			if(estado.equals(matrizTransicao.get(i).get(0)) && "&".equals(matrizTransicao.get(i).get(1))) {
				return matrizTransicao.get(i).subList(2, matrizTransicao.get(i).size());
			}
		}
		return null;
	}

	/* metodo que passa os parametros do AFND para convertê-lo em AFD e verifica, a partir do AFD criado, se a string
	 * aceita ou rejeita
	 */
	public void converter(String str) {
		Conversao c = new Conversao(conjEstados, conjAlfabeto, matrizTransicao, estadoInicial, conjEstadosFinais);
		c.VerificarStringAFD(str);
	}

	public String toString() {
		return super.toString() + "\nTransições: " + matrizTransicao;
	}
}
