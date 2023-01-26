package br.com.agenda.AgendaTelefonica.util;

import br.com.agenda.AgendaTelefonica.model.Contato;

public class Conversor {
	
	public static String ehFavorito(Boolean boo) {
		if(boo==true) {
			return "FAVORITO!";
		}else {
			return "";
		}
	}
	
	public static void formatoContato(Contato contt) {
		System.out.println("ID:       "+contt.getId()
						+ "\nNome:    "+contt.getNome()
						+"\nNúmero:   "+contt.getTelefone()
						+"\nTipo:     "+contt.getTipo()
						+"\nE-mail:   "+contt.getEmail()
						+"\nFavorito: "+ehFavorito(contt.getFavorito())
						+"\n");
	}
}
