package br.com.agenda.AgendaTelefonica.application;

import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.agenda.AgendaTelefonica.DAO.ContatoDAO;
import br.com.agenda.AgendaTelefonica.model.Contato;
import br.com.agenda.AgendaTelefonica.util.JpaUtil;

public class Application {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EntityManager em = JpaUtil.getEntityManager();

		var contt = new ContatoDAO(em);
		
		while(true) {
			System.out.print("O que fazer? " 
					+ "\nSalvar novo contato     - 1" 
					+ "\nListar contatos         - 2"
					+ "\nListar favoritos        - 3" 
					+ "\nMostrar contato         - 4" 
					+ "\nMostrar contato por ID  - 5"
					+ "\nAlterar contato         - 6"
					+ "\nDeletar contato         - 7" 
					+ "\nR: ");
			int opcao = sc.nextInt();
			sc.nextLine();
			
			em.getTransaction().begin();
			switch (opcao) {
			case 1:
				System.out.print("Nome: ");
				String nome = sc.nextLine();
				System.out.print("Telefone: ");
				String telefone = sc.nextLine();
				System.out.print("Tipo: ");
				String tipo = sc.nextLine();
				System.out.print("E-mail: ");
				String email = sc.nextLine();
				System.out.print("Favorito? [s]/[n] ");
				String favorito = sc.nextLine();
				contt.cadatrar(new Contato(nome, telefone, tipo, email, null), favorito);
				break;
			case 2:
				contt.listar();
				break;
			case 3:
				contt.listarFavorito();
				break;
			case 4:
				System.out.print("Nome: ");
				nome = sc.nextLine();
				contt.buscarPorNome(nome);
				break;
			case 5:
				System.out.print("ID: ");
				Long id = sc.nextLong();
				contt.buscarId(id);
				sc.nextLine();
				break;
			case 6:
				contt.listar();
				System.out.print("\nNome do contato: ");
				nome = sc.nextLine();
				System.out.print("O que alterar: " 
						+ "\nNome     - 1" 
						+ "\nNúmero   - 2" 
						+ "\nTipo     - 3"
						+ "\nE-mail   - 4" 
						+ "\nFavorito - 5"
						+ "\nR: ");
				Integer n = sc.nextInt();
				if (n == 5) {
					contt.atualizar(nome, n, null);
				} else {
					System.out.print("Dado:");
					sc.nextLine();
					String dado = sc.nextLine();
					contt.atualizar(nome, n, dado);
				}
				break;
			case 7:
				contt.listar();
				System.out.print("Nome: ");
				nome = sc.nextLine();
				contt.deletar(nome);
				break;
				default:
					System.out.println("Opção inválida");
					continue;
			}
			
			em.getTransaction().commit();
			
			System.out.print("\nMais alguma coisa? [s]/[n] ");
			String resp = sc.nextLine();
			if (resp.equalsIgnoreCase("n"))
				break;
			
			
		}
		
		em.close();
		sc.close();
	}

}
