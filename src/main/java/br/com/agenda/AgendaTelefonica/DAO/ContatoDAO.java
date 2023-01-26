package br.com.agenda.AgendaTelefonica.DAO;

import javax.persistence.EntityManager;

import br.com.agenda.AgendaTelefonica.model.Contato;
import br.com.agenda.AgendaTelefonica.util.Conversor;

public class ContatoDAO {
	private EntityManager em;
	
	public ContatoDAO(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadatrar(Contato contt, String favorito) {
		if (favorito.equalsIgnoreCase("s")) {
			contt.setFavorito(true);
		} else {
			contt.setFavorito(false);
		}
		
		em.persist(contt);
		System.out.println("Contato " + contt.getNome() + " salvo com sucesso!");
	}

	public void listar() {
		em.createQuery("SELECT c FROM Contato c", Contato.class)
		.getResultList().forEach(x -> Conversor.formatoContato(x));
	}

	public void listarFavorito() {
		em.createQuery("SELECT c FROM Contato c WHERE FAVORITO = ?1", Contato.class)
		.setParameter(1, true)
		.getResultList().forEach(x -> Conversor.formatoContato(x));
	}

	public void buscarPorNome(String nome) {
		Conversor.formatoContato(em
				.createQuery("SELECT c FROM Contato c WHERE NOME = ?1", Contato.class)
				.setParameter(1, nome)
				.getSingleResult());
	}
	
	public void buscarId(Long id) {
		Conversor.formatoContato(em.find(Contato.class, id));
	}

	public void atualizar(String nome, Integer n, String dado) {
		Contato contt = em
				.createQuery("SELECT c FROM Contato c WHERE NOME = ?1", Contato.class)
				.setParameter(1, nome)
				.getSingleResult();
		
		switch (n) {
		case 1:
			contt.setNome(dado);
			break;
		case 2:
			contt.setTelefone(dado);
			break;
		case 3:
			contt.setTipo(dado);
			break;
		case 4:
			contt.setEmail(dado);
			break;
		case 5:
			if(contt.getFavorito()==true) {
				contt.setFavorito(false);
			}else {
				contt.setFavorito(true);
			}
			break;
			default:
				System.out.println("Opção inválida");
				break;
		}
		
		em.merge(contt);
		System.out.println("Contato " + contt.getNome() + " atualizado com sucesso!");
	}

	public void deletar(String nome) {
		Contato contt = em
				.createQuery("SELECT c FROM Contato c WHERE NOME = ?1", Contato.class)
				.setParameter(1, nome)
				.getSingleResult();
		
		contt = em.merge(contt);
		em.remove(contt);

		System.out.println("Contato " + contt.getNome() + " deletado com sucesso!");
	}
}
