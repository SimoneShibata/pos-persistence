package posjava.persistence.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Access(AccessType.FIELD)
public class EmpregadoMixedAccess {

	private static final String CODIGO_AREA_LOCAL = "44";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @Transient // não cria no banco
	private transient String telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Access(AccessType.PROPERTY)
	@Column(name = "telefone")
	protected String getTelefoneForDb() {
		if (this.telefone.length() == 9) {
			return CODIGO_AREA_LOCAL + this.telefone;
		} else {
			return this.telefone;
		}
	}

	protected void setTelefoneForDb(String numeroTelefone) {
		if (numeroTelefone.startsWith(CODIGO_AREA_LOCAL)) {
			this.telefone = numeroTelefone
					.substring(CODIGO_AREA_LOCAL.length());
		}
		this.telefone = numeroTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
