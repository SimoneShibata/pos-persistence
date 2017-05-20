package posjava.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import posjava.persistence.entities.Empregado;
import posjava.persistence.entities.Garagem;
import posjava.persistence.entities.Projeto;
import posjava.persistence.entities.Todo;

public class Exemplo2 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("posjavaPU");
		EntityManager em = emf.createEntityManager();

		// --- INICIO DA TRANSAÇÃO
		EntityTransaction tx1 = em.getTransaction();
		tx1.begin();
		
		for (int i = 1; i <= 10; i++) {
			// Criar 10 garagens
			Garagem garagem = new Garagem();
			garagem.setNumero(i);
			garagem.setLocalizacao("A"+i);
			em.persist(garagem);
		}
		
		for (int i = 1; i <= 5; i++) {
			// Criar 5 projetos
			Projeto projeto = new Projeto();
			projeto.setNome("PROJETO NUMERO " + i);
			em.persist(projeto);
		}
		
		for (int i = 1; i <= 4; i++) {
			// Criar 4 departamentos
			Projeto projeto = new Projeto();
			projeto.setNome("DEPARTAMENTO " + i);
			em.persist(projeto);
		}
		
		for (int i = 1; i <= 8; i++) {
			// Criar 8 Empregados
			Empregado empregado = new Empregado();
			empregado.setNome("EMPREGADO " + i);
			empregado.setComentario("COMENTÁRIO PARA EMPREGADO NUMERO " + i);
			empregado.setSalario(i*1000);
			em.persist(empregado);
		}
		// --- TERMINO DA TRANSAÇÃO
		
		tx1.commit();
		
		
		// Associar

		// --- INICIO DA TRANSAÇÃO
		EntityTransaction tx2 = em.getTransaction();
		tx2.begin();
		// Atribuir uma garagem para cada empregado
		for (int i = 1; i <= 8; i++) {
			Garagem garagem = em.find(Garagem.class, Long.parseLong(i+""));
			System.out.println(String.format("GARAGEM findOne de %d", Long.parseLong(i+"")));

			Empregado empregado = em.find(Empregado.class, Long.parseLong(i+""));
			System.out.println(String.format("EMPREGADO findOne de %d", Long.parseLong(i+"")));
			
			garagem.setEmpregado(empregado);
			empregado.setGaragem(garagem);
			em.persist(garagem);
			em.persist(empregado);

		}
		tx2.commit();
		// --- TERMINO DA TRANSAÇÃO

		// Adicionar cada empregado a um departamento
		// Adicionar cada empregado a um projeto
		
		
	}
}
