package Exceptions;

import java.util.List;

//Objetivo: verificar se as transições possuem os estados e símbolo presentes no conjunto de estados e alfabeto, respectivamente
//e aceitando o "&" como símbolo do alfabeto como sendo uma transição epsilon
public class VerificaTransicoesAFNDException extends Exception{

	private static final long serialVersionUID = 1L;

	public static void verificaEstadoFinal(List<String> conjunto, List<String> alfabeto, List<List<String>> transicao) throws VerificaTransicoesAFNDException {
		boolean estado = false, simbolo = false;
		
		for (int i = 0; i < transicao.size(); i++) {
			for (int j = 0; j < transicao.get(i).size(); j++) {
				estado = false;
				simbolo = false;
				if(j == 1) {
					for (int l = 0; l < alfabeto.size(); l++) {
						if(transicao.get(i).get(j).equals(alfabeto.get(l)) || "&".equals(transicao.get(i).get(j))) {
							simbolo = true;
						}
						estado = true;
					}
				}else {
					for (int k = 0; k < conjunto.size(); k++) {
						//System.out.println(transicao.get(i).get(j));
						//System.out.println(conjunto.get(k));
						if(transicao.get(i).get(j).equals(conjunto.get(k))) {
							estado = true;
						}
						simbolo = true;
					}
				}
				if(!(estado) || !(simbolo)) {
					System.out.print("Transição " + transicao.get(i) + " não aceita " + transicao.get(i).get(j));
					if(!(estado) && j == 0) {
						System.out.println(" como estado!");
					}else if(!(simbolo)) {
						System.out.println(" como alfabeto!");
					}else {
						System.out.println(" como estado final!");
					}
					throw new VerificaTransicoesAFNDException();
				}
			}
		}
		
		
	}
}
