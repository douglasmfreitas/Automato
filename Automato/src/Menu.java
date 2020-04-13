import java.util.Scanner;

public class Menu {
	public Menu() {
		int valor;
		String estadoAFND = null, alfabetoAFND = null, transicaoAFND = null, estadoInicialAFND = null, estadosFinaisAFND = null;
		String estadoAFD = null, alfabetoAFD = null, transicaoAFD = null, estadoInicialAFD = null, estadosFinaisAFD = null;
		String stringAFND, stringAFD, stringAFNDtoAFD;
		Scanner continuar = new Scanner(System.in);
		do {
			System.out.println("\n\tMenu de opções\n");
			System.out.println("1. Definir AFND");
			System.out.println("2. Verificar string AFND");
			System.out.println("3. Definir AFD");
			System.out.println("4. Verificar string AFD");
			System.out.println("5. Conveter AFND para AFD");
			System.out.println("0. Sair");
			
			valor = continuar.nextInt();
			
			Scanner str;
			switch(valor) {
				case 1:
					System.out.println("Informe o conjunto de estados! Separe os estados por \",\"\nEx: q0,q1,q2");
					str = new Scanner(System.in);
					estadoAFND = str.next();
					
					System.out.println("Informe o alfabeto! Separe os símbolos por \",\"\nEx: 0,1,2");
					str = new Scanner(System.in);
					alfabetoAFND = str.next();
					
					System.out.println("Informe as transições! Utilize & para transições epsilon\n"
										+ "Separe as transições por \";\" e cada elemento da trasição por \",\"/n"
										+ "Ex: q0,0,q1;q0,1q1,q2;q1,&,q2");
					str = new Scanner(System.in);
					transicaoAFND = str.next();
					
					System.out.println("Informe o estado inicial! Ex: q0");
					str = new Scanner(System.in);
					estadoInicialAFND = str.next();
					
					System.out.println("Informe o(s) estado(s) final(is)! Separe cada estado por \",\"\nEx: q1,q2");
					str = new Scanner(System.in);
					estadosFinaisAFND = str.next();
					
					AFND afnd = new AFND(estadoAFND, alfabetoAFND, transicaoAFND, estadoInicialAFND, estadosFinaisAFND);
					break;
				
				case 2:
					if(estadosFinaisAFND == null) {
						System.out.println("Defina um AFND primeiro!");
					}else {
						System.out.println("Informe uma string para verificar!");
						str = new Scanner(System.in);
						stringAFND = str.next();
						AFND afnd2 = new AFND(estadoAFND, alfabetoAFND, transicaoAFND, estadoInicialAFND, estadosFinaisAFND);
						afnd2.verificaString(stringAFND);
					}
					break;
					
				case 3:
					System.out.println("Informe o conjunto de estados! Separe os estados por \",\"\nEx: q0,q1,q2");
					str = new Scanner(System.in);
					estadoAFD = str.next();
					
					System.out.println("Informe o alfabeto! Separe os símbolos por \",\"\nEx: 0,1,2");
					str = new Scanner(System.in);
					alfabetoAFD = str.next();
					
					System.out.println("Informe as transições! Separe as transições por \";\" e cada elemento da trasição por \",\"/n"
										+ "Ex: q0,0,q1;q0,1q1,q2;q1,&,q2");
					str = new Scanner(System.in);
					transicaoAFD = str.next();
					
					System.out.println("Informe o estado inicial! Ex: q0");
					str = new Scanner(System.in);
					estadoInicialAFD = str.next();
					
					System.out.println("Informe o(s) estado(s) final(is)! Separe cada estado por \",\"\nEx: q1,q2");
					str = new Scanner(System.in);
					estadosFinaisAFD = str.next();
					
					AFND afd = new AFND(estadoAFND, alfabetoAFND, transicaoAFND, estadoInicialAFND, estadosFinaisAFND);
					break;
				
				case 4:
					if(estadosFinaisAFD == null) {
						System.out.println("Defina um AFD primeiro!");
					}else {
						System.out.println("Informe uma string para verificar!");
						str = new Scanner(System.in);
						stringAFD = str.next();
						AFD afd2 = new AFD(estadoAFD, alfabetoAFD, transicaoAFD, estadoInicialAFD, estadosFinaisAFD);
						afd2.verificaString(stringAFD);
					}
					break;
					
				case 5:
					if(estadosFinaisAFND == null) {
						System.out.println("Defina um AFND primeiro!");
					}else {
						System.out.println("Informe uma string para verificar!");
						str = new Scanner(System.in);
						stringAFNDtoAFD = str.next();
						AFND afndtoafd = new AFND(estadoAFND, alfabetoAFND, transicaoAFND, estadoInicialAFND, estadosFinaisAFND);
						afndtoafd.converter(stringAFNDtoAFD);
					}
					break;
					
				case 0:
					break;
					
				default:
					System.out.println("Digite uma opção válida");
					break;
			}
			
		}while(valor != 0);
		continuar.close();
	}
}
