package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.granbery.sisestagio.dao.ProjetoFinalDaoImpl;
import br.edu.granbery.sisestagio.model.ProjetoFinal;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.validators.ProtocoloValidator;

@ManagedBean(name = "projetoFinalBean")
@RequestScoped
public class ProjetoFinalBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProjetoFinal projetoFinal;
	private ProjetoFinalDaoImpl projetoFinalDaoImpl;
	private List<ProjetoFinal> filteredProjetoFinal;

	public ProjetoFinalBean() {
		this.projetoFinal = new ProjetoFinal();
		this.projetoFinalDaoImpl = new ProjetoFinalDaoImpl();
	}

	public void salvaProjetoFinal() {
		try {
			projetoFinalDaoImpl.getSession();
			
			projetoFinalDaoImpl.save(projetoFinal);
			MessagesUtil
					.info("Projeto Final para Aluno "
							+ projetoFinal.getAluno().getNome()
							+ " salvo com sucesso.");
			this.projetoFinal = new ProjetoFinal();
		} catch (Exception e) {
			MessagesUtil.error("Erro ao salvar Projeto Final para Aluno "
					+ projetoFinal.getAluno().getNome()
					+ ": Já tem Projeto Final cadastro no banco de dados!");
		} finally {
			projetoFinalDaoImpl.sessionClose();
		}

	}

	public void updateProjetoFinal() {
		try {

			projetoFinalDaoImpl.getSession();
			
			if (projetoFinalDaoImpl.findFields("idProjetoFinal",
					projetoFinal.getIdProjetoFinal(), "protocolo",
					projetoFinal.getProtocolo()) == null) {
				ProtocoloValidator.getInstance().ProtocoloValidatorUpdate(
						projetoFinal.getProtocolo());
			}

			projetoFinalDaoImpl.update(projetoFinal);
			MessagesUtil.info("Dados do Projeto Final para Aluno "
					+ projetoFinal.getAluno().getNome()
					+ " alterado com sucesso.");
			this.projetoFinal = new ProjetoFinal();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil
						.error("Erro ao alterar os dados do Projeto Final para Aluno "
								+ projetoFinal.getAluno().getNome()
								+ ": Já tem Projeto Finalcadastro no banco de dados!");
			}
		} finally {
			projetoFinalDaoImpl.sessionClose();
		}
	}

	public void excluir() {
		try {
			
			projetoFinalDaoImpl.getSession();
			
			projetoFinalDaoImpl.remove(projetoFinal);
			MessagesUtil.info("Projeto Final do Aluno "
					+ projetoFinal.getAluno().getNome()
					+ " excluindo com sucesso.");
			this.projetoFinal = new ProjetoFinal();
			this.filteredProjetoFinal = projetoFinalDaoImpl
					.findAll("protocolo");
		} catch (Exception e) {
			MessagesUtil.error("Erro ao excluir Projeto Final do Aluno "
					+ projetoFinal.getAluno().getNome());
		} finally {
			projetoFinalDaoImpl.sessionClose();
		}
	}

	public List<ProjetoFinal> getProjetoFinais() {
		projetoFinalDaoImpl.getSession();
		
		List<ProjetoFinal> projetoFinals = projetoFinalDaoImpl.findAll("protocolo");
		
		projetoFinalDaoImpl.sessionClose();
		
		return projetoFinals;
	}

	public ProjetoFinal getProjetoFinal() {
		return projetoFinal;
	}

	public void setProjetoFinal(ProjetoFinal projetoFinal) {
		this.projetoFinal = projetoFinal;
	}

	public List<ProjetoFinal> getFilteredProjetoFinal() {
		return filteredProjetoFinal;
	}

	public void setFilteredProjetoFinal(List<ProjetoFinal> filteredProjetoFinal) {
		this.filteredProjetoFinal = filteredProjetoFinal;
	}

}
