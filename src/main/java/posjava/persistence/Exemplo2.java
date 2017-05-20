package posjava.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import posjava.persistence.entities.Departamento;
import posjava.persistence.entities.Empregado;
import posjava.persistence.entities.Garagem;
import posjava.persistence.entities.Projeto;

public class Exemplo2 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("posjavaPU");
		EntityManager em = emf.createEntityManager();

		// --- INICIO DA TRANSAÇÃO
		EntityTransaction transaction1 = em.getTransaction();
		transaction1.begin();

		criaGaragens(em);

		criaProjetos(em);

		criaDepartamentos(em);

		criaEmpregados(em);
		
		transaction1.commit();
		// --- TERMINO DA TRANSAÇÃO

		// Associar

		// --- INICIO DA TRANSAÇÃO
		EntityTransaction transaction2 = em.getTransaction();
		transaction2.begin();
		// Atribuir uma garagem para cada empregado
		criaAssociacaoEmpregadoGaragem(em);
		transaction2.commit();
		// --- TERMINO DA TRANSAÇÃO

		// --- INICIO DA TRANSAÇÃO
		EntityTransaction transaction3 = em.getTransaction();
		transaction3.begin();
		// Adicionar cada empregado a um departamento
		criaAssociacaoEmpregadoDepartamento(em);
		transaction3.commit();
		// --- TERMINO DA TRANSAÇÃO

		// --- INICIO DA TRANSAÇÃO
		EntityTransaction transaction4 = em.getTransaction();
		transaction4.begin();
		// Adicionar cada empregado a um projeto
		criaAssociacaoEmpregadoProjeto(em);
		transaction4.commit();
		// --- TERMINO DA TRANSAÇÃO

		// --- INICIO DA TRANSAÇÃO
		EntityTransaction transaction5 = em.getTransaction();
		transaction5.begin();
		// Adicionar cada empregado a um projeto
		transaction5.commit();
		// --- TERMINO DA TRANSAÇÃO
	}

	private static void criaAssociacaoEmpregadoProjeto(EntityManager em) {
		for (int i = 1; i <= 8; i++) {
			Empregado empregado = em.find(Empregado.class,
					Long.parseLong(String.format("%d", i)));
			System.out.println(String.format("EMPREGADO findOne de %d", i));

			Random r = new Random();
			Projeto projeto = em.find(Projeto.class,
					Long.parseLong(String.format("%d", r.nextInt(5) + 1)));
			System.out.println(String.format("DEPARTAMENTO findOne de %d",
					r.nextInt(5) + 1));

			Collection<Projeto> projetos = new ArrayList<Projeto>();
			projetos.add(projeto);
			Collection<Empregado> empregados = new ArrayList<Empregado>();
			empregados.add(empregado);

			empregado.setProjetos(projetos);
			projeto.setEmpregados(empregados);
			em.persist(empregado);

		}
	}

	private static void criaAssociacaoEmpregadoDepartamento(EntityManager em) {
		for (int i = 1; i <= 8; i++) {
			Empregado empregado = em.find(Empregado.class,
					Long.parseLong(String.format("%d", i)));
			System.out.println(String.format("EMPREGADO findOne de %d", i));

			Random r = new Random();
			Departamento departamento = em.find(Departamento.class,
					Long.parseLong(String.format("%d", r.nextInt(4) + 1)));
			System.out.println(String.format("DEPARTAMENTO findOne de %d",
					r.nextInt(4) + 1));

			empregado.setDepartamento(departamento);
			em.persist(empregado);

		}
	}

	private static void criaAssociacaoEmpregadoGaragem(EntityManager em) {
		for (int i = 1; i <= 8; i++) {
			Garagem garagem = em.find(Garagem.class, Long.parseLong(i + ""));
			System.out.println(String.format("GARAGEM findOne de %d", i));

			Empregado empregado = em.find(Empregado.class,
					Long.parseLong(i + ""));
			System.out.println(String.format("EMPREGADO findOne de %d", i));

			garagem.setEmpregado(empregado);
			empregado.setGaragem(garagem);
			em.persist(garagem);
			em.persist(empregado);

		}
	}

	private static void criaEmpregados(EntityManager em) {
		for (int i = 1; i <= 8; i++) {
			// Criar 8 Empregados
			Empregado empregado = new Empregado();
			empregado.setNome("EMPREGADO " + i);
			empregado.setComentario("COMENTÁRIO PARA EMPREGADO NUMERO " + i);
			empregado.setSalario(i * 1000);
			em.persist(empregado);
		}
	}

	private static void criaDepartamentos(EntityManager em) {
		for (int i = 1; i <= 4; i++) {
			// Criar 4 departamentos
			Departamento departamento = new Departamento();
			departamento.setNome("DEPARTAMENTO " + i);
			em.persist(departamento);
		}
	}

	private static void criaProjetos(EntityManager em) {
		for (int i = 1; i <= 5; i++) {
			// Criar 5 projetos
			Projeto projeto = new Projeto();
			projeto.setNome("PROJETO NUMERO " + i);
			em.persist(projeto);
		}
	}

	private static void criaGaragens(EntityManager em) {
		for (int i = 1; i <= 10; i++) {
			// Criar 10 garagens
			Garagem garagem = new Garagem();
			garagem.setNumero(i);
			garagem.setLocalizacao("A" + i);
			em.persist(garagem);
		}
	}
}
