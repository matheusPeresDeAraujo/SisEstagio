package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.granbery.sisestagio.dao.ProjetoEstagioDaoImpl;
import br.edu.granbery.sisestagio.model.ProjetoEstagio;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.validators.ProtocoloValidator;

@ManagedBean(name = "projetoEstagioBean")
@RequestScoped
public class ProjetoEstagioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProjetoEstagio projetoEstagio;
	private ProjetoEstagioDaoImpl projetoEstagioDaoImpl;
	private List<ProjetoEstagio> filteredProjetoEstagio;

	public ProjetoEstagioBean() {
		this.projetoEstagio = new ProjetoEstagio();
		this.projetoEstagioDaoImpl = new ProjetoEstagioDaoImpl();
	}

	public void salvaProjetoEstagio() {
		try {

			projetoEstagioDaoImpl.getSession();

			projetoEstagioDaoImpl.save(projetoEstagio);

			MessagesUtil.info("Projeto de Estágio para Aluno "
					+ projetoEstagio.getAluno().getNome()
					+ " salvo com sucesso.");

			this.projetoEstagio = new ProjetoEstagio();
		} catch (Exception e) {
			MessagesUtil
					.error("Erro ao salvar Projeto de Estágio para Aluno "
							+ projetoEstagio.getAluno().getNome()
							+ ": Já tem Projeto de Estágio cadastro no banco de dados!");

		} finally {
			projetoEstagioDaoImpl.sessionClose();
		}

	}

	public void updateProjetoEstagio() {
		try {
			projetoEstagioDaoImpl.getSession();

			if (projetoEstagioDaoImpl.findFields("idProjetoDeEstagio",
					projetoEstagio.getIdProjetoDeEstagio(), "protocolo",
					projetoEstagio.getProtocolo()) == null) {
				ProtocoloValidator.getInstance().ProtocoloValidatorUpdate(
						projetoEstagio.getProtocolo());
			}

			projetoEstagioDaoImpl.update(projetoEstagio);

			MessagesUtil.info("Dados do Projeto de Estágio para Aluno "
					+ projetoEstagio.getAluno().getNome()
					+ " alterado com sucesso.");

			this.projetoEstagio = new ProjetoEstagio();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil
						.error("Erro ao alterar os dados do Projeto de Estágio para Aluno "
								+ projetoEstagio.getAluno().getNome()
								+ ": Já tem Projeto de Estágio cadastro no banco de dados!");
			}
		} finally {
			projetoEstagioDaoImpl.sessionClose();
		}

	}

	public void excluir() {
		try {
			projetoEstagioDaoImpl.getSession();

			projetoEstagioDaoImpl.remove(projetoEstagio);
			MessagesUtil.info("Projeto de Estágio do Aluno "
					+ projetoEstagio.getAluno().getNome()
					+ " excluindo com sucesso.");

			this.projetoEstagio = new ProjetoEstagio();

			this.filteredProjetoEstagio = projetoEstagioDaoImpl
					.findAll("protocolo");

		} catch (Exception e) {
			MessagesUtil.error("Erro ao excluir Projeto de Estágio do Aluno "
					+ projetoEstagio.getAluno().getNome());
		} finally {
			projetoEstagioDaoImpl.sessionClose();
		}
	}

	public List<ProjetoEstagio> getProjetoEstagios() {
		projetoEstagioDaoImpl.getSession();

		List<ProjetoEstagio> projetoEstagios = projetoEstagioDaoImpl
				.findAll("protocolo");

		projetoEstagioDaoImpl.sessionClose();

		return projetoEstagios;
	}

	public ProjetoEstagio getProjetoEstagio() {
		return projetoEstagio;
	}

	public void setProjetoEstagio(ProjetoEstagio projetoEstagio) {
		this.projetoEstagio = projetoEstagio;
	}

	public List<ProjetoEstagio> getFilteredProjetoEstagio() {
		return filteredProjetoEstagio;
	}

	public void setFilteredProjetoEstagio(
			List<ProjetoEstagio> filteredProjetoEstagio) {
		this.filteredProjetoEstagio = filteredProjetoEstagio;
	}

}
