import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Conversao{
	String conjEstados, conjAlfabeto, estadoInicial, conjEstadosFinais, matrizTransicao;
	List<List<String>> transicaotmp;
	List<List<String>> estadosFinaisAFD = new ArrayList<List<String>>();
	List<List<String>> estadosAFD = new ArrayList<List<String>>();
	List<List<List<String>>> matrizTransicoes = new ArrayList<List<List<String>>>();
	List<List<List<String>>> transicaoAFD = new ArrayList<List<List<String>>>();
	List<List<String>> transicaoEpsilon = new ArrayList<List<String>>();
	
	public Conversao(List<String> estados, List<String> alfabeto, List<List<String>> transicao, String estadoInicial, List<String> estadosFinais) {
		this.estadoInicial = estadoInicial;
		int count = 0;
		
		for (int i = 0; i < alfabeto.size(); i++) {
			if(i == 0) {
				conjAlfabeto = alfabeto.get(i);
			}else {
				conjAlfabeto = conjAlfabeto + alfabeto.get(i);
			}
			if(i < alfabeto.size() - 1) {
				conjAlfabeto = conjAlfabeto + ",";
			}
		}
		
		for (int i = 0; i < transicao.size(); i++) {
			if("&".equals(transicao.get(i).get(1))) {
				transicaoEpsilon.add(transicao.get(i));
			}
		}
		
		do {
			if(count == 0) {
				estadosAFD.add(Arrays.asList(estadoInicial));
			}
			
			transicaotmp = new ArrayList<List<String>>();
			transicaotmp.add(estadosAFD.get(count));
			for (int i = 0; i < alfabeto.size(); i++) {
				for (int j = 0; j < transicao.size(); j++) {
					for (int k = 0; k < estadosAFD.get(count).size(); k++) {
						if(estadosAFD.get(count).get(k).equals(transicao.get(j).get(0)) && alfabeto.get(i).equals(transicao.get(j).get(1))) {
							if(transicaotmp.size() == i + 1) {
								transicaotmp.add(transicao.get(j).subList(2, transicao.get(j).size()));
							}else {
								List<String> tmp = new ArrayList<String>();
								tmp.addAll(transicaotmp.get(i + 1));
								for (int l = 2; l < transicao.get(j).size(); l++) {
									boolean existe = false;
									for (int m = 0; m < tmp.size(); m++) {
										if(tmp.get(m).equals(transicao.get(j).get(l))) {
											existe = true;
											break;
										}
									}
									if(!existe) {
										tmp.add(transicao.get(j).get(l));
									}
								}
								transicaotmp.set(i + 1, tmp);
							}
						}
					}
				}
				transicaotmp.set(i + 1, eClose(transicaotmp.get(i+1)));
				Collections.sort(transicaotmp.get(i+1));
				
				if(transicaotmp.size() - 1 == i){
					transicaotmp.add(Arrays.asList("{}"));
				}
			}
			
			matrizTransicoes.add(transicaotmp);
			
			for (int i = 1; i < transicaotmp.size(); i++) {
				boolean isExiste = false;
				for (int j = 0; j < estadosAFD.size(); j++) {
					if(estadosAFD.get(j).equals(transicaotmp.get(i))) {
						isExiste = true;
						break;
					}
				}

				if(isExiste == false) {
					estadosAFD.add(transicaotmp.get(i));
				}
			}
			
			count++;
		} while (count < estadosAFD.size());
		
		for (int i = 0; i < estadosAFD.size(); i++) {
			for (int j = 0; j < estadosAFD.get(i).size(); j++) {
				boolean existe = false;
				for (int k = 0; k < estadosFinais.size(); k++) {
					if(estadosAFD.get(i).get(j).equals(estadosFinais.get(k))) {
						existe = true;
						break;
					}
				}
				if(existe) {
					estadosFinaisAFD.add(estadosAFD.get(i));
				}
			}
		}
		
		for (int i = 0; i < matrizTransicoes.size(); i++) {
			for (int j = 0; j < alfabeto.size(); j++) {
				transicaotmp = new ArrayList<List<String>>();
				transicaotmp.add(matrizTransicoes.get(i).get(0));
				transicaotmp.add(Arrays.asList(alfabeto.get(j)));
				transicaotmp.add(matrizTransicoes.get(i).get(j+1));
				transicaoAFD.add(transicaotmp);
			}
		}
		
		conjEstados = converterEstados(estadosAFD, conjEstados);
		conjEstadosFinais = converterEstados(estadosFinaisAFD, conjEstadosFinais);
		converterTransicoes(transicaoAFD);
	}
	
	public List<String> eClose(List<String> transicao) {
		List<String> list = new ArrayList<String>();
		list.addAll(transicao);

		for (int i = 0; i < transicao.size(); i++) {
			for (int j = 0; j < transicaoEpsilon.size(); j++) {
				if(transicao.get(i).equals(transicaoEpsilon.get(j).get(0))) {
					list.add(transicaoEpsilon.get(j).get(2));
				}
			}
		}
		return list;	
	}
	
	public String converterEstados(List<List<String>> estados, String conjunto) {
		for (int i = 0; i < estados.size(); i++) {
			for (int j = 0; j < estados.get(i).size(); j++) {
				if(conjunto == null) {
					conjunto = estados.get(i).get(j);
				}else {
					conjunto = conjunto + estados.get(i).get(j);
				}
			}
			if(i < estados.size() - 1) {
				conjunto = conjunto + ",";
			}
		}
		return conjunto;
	}
	
	public void converterTransicoes(List<List<List<String>>> transicoes) {
		for (int i = 0; i < transicoes.size(); i++) {
			for (int j = 0; j < transicoes.get(i).size(); j++) {
				for (int k = 0; k < transicoes.get(i).get(j).size(); k++) {
					if(matrizTransicao == null) {
						matrizTransicao = transicoes.get(i).get(j).get(k);
					}else {
						matrizTransicao = matrizTransicao + transicoes.get(i).get(j).get(k);
					}
				}
				if(j < transicoes.get(i).size() - 1) {
					matrizTransicao = matrizTransicao + ",";
				}
			}
			if(i < transicoes.size() - 1) {
				matrizTransicao = matrizTransicao + ";";
			}
		}
	}
	
	public void VerificarStringAFD(String str) {
		AFD afndToAfd = new AFD(conjEstados, conjAlfabeto, matrizTransicao, estadoInicial, conjEstadosFinais);
		afndToAfd.verificaString(str);
	}
}
