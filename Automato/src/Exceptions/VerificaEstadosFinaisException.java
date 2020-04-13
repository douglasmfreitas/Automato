package Exceptions;

import java.util.List;

//Objetivo: verificar se o conjunto de estados finais está contido no conjuto de estados 
public class VerificaEstadosFinaisException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public static void verificaEstadoFinal(List<String> conjunto, List<String> finais) throws VerificaEstadosFinaisException {
		boolean estado = false;
		
		for (int i = 0; i < finais.size(); i++) {
			estado = false;
			for (int j = 0; j < conjunto.size(); j++) {
				if(finais.get(i).equals(conjunto.get(j))) {
					estado = true;
				}
			}
			if(!(estado)) {
				throw new VerificaEstadosFinaisException();
			}
		}
	}
}
