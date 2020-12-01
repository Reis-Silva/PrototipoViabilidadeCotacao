package cotacao.entity.gerente;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "gerentes")
@ToString(of = "email")
@Data
public class Gerente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private Integer id;
	
	@Column
	private String nomeGerente;
	
	@Column
	private String agencia;
	
	@Column
	private String email;	
}
