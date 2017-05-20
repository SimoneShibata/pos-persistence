package posjava.persistence.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPREGADOS")
// @Table(name="EMPREGADOS", schema="RH") -- caso esteja em schema diferente
// @Table(name="EMPREGADOS", catalog="RH") -- tambem pode ser usado assim
public class Empregado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID")
	private Long id;

	@Column(name = "EMP_NOME")
	private String nome;

	@Column(name = "SAL")
	private long salario;

	@Column(name = "COM")
	private String comentario;

	@ManyToOne
	@JoinColumn(name = "DEPT_ID")
	private Departamento departamento;

	@OneToOne
	@JoinColumn(name = "GRG_ID")
	private Garagem garagem;

	@ManyToMany
	@JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "EMP_ID"), inverseJoinColumns = @JoinColumn(name = "PROJ_ID"))
	private Collection<Projeto> projetos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getSalario() {
		return salario;
	}

	public void setSalario(long salario) {
		this.salario = salario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Garagem getGaragem() {
		return garagem;
	}

	public void setGaragem(Garagem garagem) {
		this.garagem = garagem;
	}

	public Collection<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(Collection<Projeto> projetos) {
		this.projetos = projetos;
	}

}
