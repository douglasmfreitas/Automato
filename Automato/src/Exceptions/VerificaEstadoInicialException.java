package Exceptions;

import java.util.List;

//Objetivo: verificar se o estado inicial pertence ao conjunto de estados

public class VerificaEstadoInicialException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static void verificaEstadoInicial(List<String> conjunto, String inicial) throws VerificaEstadoInicialException {
		boolean estado = false;
		
		for (int i = 0; i < conjunto.size(); i++) {
			if(conjunto.get(i).equals(inicial)) {
				estado = true;
			}
		}
		
		if(!(estado)) {
			throw new VerificaEstadoInicialException();
		}
	}
}
