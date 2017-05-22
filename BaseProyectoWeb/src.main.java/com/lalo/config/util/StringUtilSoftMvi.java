package com.lalo.config.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
/**
 * @author Eduardo Cruz Zamorano
 *
 */
public final class StringUtilSoftMvi {
	
	private StringUtilSoftMvi(){};
	public static final Integer INDICE_PALABRAS_REMOVIDA=1;
	public static final Integer INDICE_PALABRAS_NUEVAS=2;
	public static final String INDICE_DESCRIPCION="Descripci�n Actualizada";
	public static final String INDICE_HEADER="Header Actualizado";
	public static final Integer TIPO_DESCRIPCION=1;
	public static final Integer TIPO_HEADER=2;
	
	public static String palabrasNuevasComparaCadenas(String actual,String nueva){
		return palabrasComparaCadenasByTipo(actual,nueva,INDICE_PALABRAS_NUEVAS);
	}
	public static String palabrasRemovidasComparaCadenas(String actual,String nueva){
		return palabrasComparaCadenasByTipo(actual,nueva,INDICE_PALABRAS_REMOVIDA);
	}
	private static String palabrasComparaCadenasByTipo(String actual,String nueva,Integer tipo){
		StringBuffer sb = new StringBuffer();
		sb.append("");
		Map<Integer,List<String>> mapaComparacion ;
		mapaComparacion = StringUtilSoftMvi.comparaCadenas(actual, nueva);
		for (String palabraCambio : mapaComparacion.get(tipo)) {
			sb.append(palabraCambio).append(" ");
		}
		return sb.toString();
	}
	public static Map<Integer,List<String>> comparaCadenas(String actual,String nueva){
		Map<Integer,List<String>> salida = new HashMap<Integer,List<String>>();
		if(StringUtils.isNotEmpty(actual) && StringUtils.isNotEmpty(nueva)){
			List<String> oldList = stringToList(actual);
			List<String> newList= stringToList(nueva);
			List<String> removed = new ArrayList<String>(oldList);
			removed.removeAll(newList);
			List<String> update = new ArrayList<String>(oldList);
			update.retainAll(newList);
			List<String> added = new ArrayList<String>(newList);
			added.removeAll(oldList);
			salida.put(INDICE_PALABRAS_REMOVIDA, removed);
			salida.put(INDICE_PALABRAS_NUEVAS, added);
		}else if(StringUtils.isEmpty(actual) && StringUtils.isNotEmpty(nueva)){
			salida = new HashMap<Integer, List<String>>();
			salida.put(INDICE_PALABRAS_REMOVIDA, new ArrayList<String>());
			salida.put(INDICE_PALABRAS_NUEVAS, stringToList(nueva));
		}
		
		return salida;
//		System.out.println("Removido: ");
//		for (String string : removed) {
//			System.out.println(string);
//		}
//		System.out.println("Update: ");
//		for (String string : update) {
//			System.out.println(string);
//		}
//		System.out.println("A�adidos: ");
//		for (String string : added) {
//			System.out.println(string);
//		}
	}
	public static List<String> stringToList(String entrada){
		List <String> salida = new ArrayList<String>();
		StringTokenizer st;
		Integer total;
		//limpia cadena si tiene HTML
		if (StringUtils.isNotBlank(entrada)) {
			if (entrada.contains("<") && entrada.contains(">")) {
				entrada = html2text(entrada);
			}
		}
		st = new StringTokenizer(entrada);
		total=st.countTokens();
		for (int i = 0; i < total; i++) {
			salida.add(st.nextToken());
		}
		return salida;
	}
	public static String html2text(String html) {
		Jsoup.parse(html).attributes();
		return Jsoup.parse(html).text();
	}
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
