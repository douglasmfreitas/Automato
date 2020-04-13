package Exceptions;

import java.util.List;

//Objetivo: verificar se as transições possuem os estados e símbolo presentes no conjunto de estados e alfabeto, respectivamente
public class VerificaTransicoesAFDException extends Exception{

	private static final long serialVersionUID = 1L;

	public static void verificaEstadoFinal(List<String> estados, List<String> alfabeto, List<List<String>> transicao) throws VerificaTransicoesAFDException {
		boolean estado = false, simbolo = false;
		
		for (int h = 0; h < transicao.size(); h++) {
			if(transicao.get(h).size() > 3) {
				System.out.println("Transição " + transicao.get(h) + " errada!");
				throw new VerificaTransicoesAFDException();
			}
		}
		
		for (int i = 0; i < transicao.size(); i++) {
			for (int j = 0; j < transicao.get(i).size(); j++) {
				estado = false;
				simbolo = false;
				if(j == 1) {
					for (int l = 0; l < alfabeto.size(); l++) {
						if(transicao.get(i).get(j).equals(alfabeto.get(l))) {
							simbolo = true;
						}
						estado = true;
					}
				}else {
					for (int k = 0; k < estados.size(); k++) {
						if(transicao.get(i).get(j).equals(estados.get(k))) {
							estado = true;
						}
						simbolo = true;
					}
				}
				if(!(estado) || !(simbolo)) {
					System.out.println("Transição " + transicao.get(i) + " não aceita " + transicao.get(i).get(j));
					if(!(estado) && j == 0) {
						System.out.println(" como estado!");
					}else if(!(simbolo)) {
						System.out.println(" como alfabeto!");
					}else {
						System.out.println(" como estado final!");
					}
					throw new VerificaTransicoesAFDException();
				}
			}
		}
		
		
	}
}
