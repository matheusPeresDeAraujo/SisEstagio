package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.granbery.sisestagio.dao.RelatorioAcompanhamentoDaoImpl;
import br.edu.granbery.sisestagio.model.RelatorioAcompanhamento;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.validators.ProtocoloValidator;

@ManagedBean(name = "relatorioAcompanhamentoBean")
@RequestScoped
public class RelatorioAcompanhamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private RelatorioAcompanhamento relatorioAcompanhamento;
	private RelatorioAcompanhamentoDaoImpl relatorioAcompanhamentoDaoImpl;
	private List<RelatorioAcompanhamento> filteredRelatorioAcompanhamento;

	public RelatorioAcompanhamentoBean() {
		this.relatorioAcompanhamento = new RelatorioAcompanhamento();
		this.relatorioAcompanhamentoDaoImpl = new RelatorioAcompanhamentoDaoImpl();
	}

	public void salvaRelatorioAcompanhamento() {
		try {

			relatorioAcompanhamentoDaoImpl.getSession();

			relatorioAcompanhamentoDaoImpl.save(relatorioAcompanhamento);
			MessagesUtil.info("Relatório de Acompanhamento para Aluno "
					+ relatorioAcompanhamento.getAluno().getNome()
					+ " salvo com sucesso.");
			this.relatorioAcompanhamento = new RelatorioAcompanhamento();
		} catch (Exception e) {
			MessagesUtil
					.error("Erro ao salvar Relatório de Acompanhamento para Aluno "
							+ relatorioAcompanhamento.getAluno().getNome()
							+ ": Já tem Relatório de Acompanhamento cadastro no banco de dados!");
		} finally {
			relatorioAcompanhamentoDaoImpl.sessionClose();
		}

	}

	public void updateRelatorioAcompanhamento() {
		try {

			relatorioAcompanhamentoDaoImpl.getSession();

			if (relatorioAcompanhamentoDaoImpl.findFields(
					"idRelatorioDeAcompanhamento",
					relatorioAcompanhamento.getIdRelatorioDeAcompanhamento(),
					"protocolo", relatorioAcompanhamento.getProtocolo()) == null) {
				ProtocoloValidator.getInstance().ProtocoloValidatorUpdate(
						relatorioAcompanhamento.getProtocolo());
			}

			relatorioAcompanhamentoDaoImpl.update(relatorioAcompanhamento);
			MessagesUtil
					.info("Dados do Relatório de Acompanhamento para Aluno "
							+ relatorioAcompanhamento.getAluno().getNome()
							+ " alterado com sucesso.");
			this.relatorioAcompanhamento = new RelatorioAcompanhamento();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil
						.error("Erro ao alterar os dados do Relatório de Acompanhamento para Aluno "
								+ relatorioAcompanhamento.getAluno().getNome()
								+ ": Já tem Relatório de Acompanhamentocadastro no banco de dados!");
			}
		} finally {
			relatorioAcompanhamentoDaoImpl.sessionClose();
		}
	}

	public void excluir() {
		try {

			relatorioAcompanhamentoDaoImpl.getSession();

			relatorioAcompanhamentoDaoImpl.remove(relatorioAcompanhamento);
			MessagesUtil.info("Relatório de Acompanhamento do Aluno "
					+ relatorioAcompanhamento.getAluno().getNome()
					+ " excluindo com sucesso.");
			this.relatorioAcompanhamento = new RelatorioAcompanhamento();
			this.filteredRelatorioAcompanhamento = relatorioAcompanhamentoDaoImpl
					.findAll("protocolo");
		} catch (Exception e) {
			MessagesUtil
					.error("Erro ao excluir Relatório de Acompanhamento do Aluno "
							+ relatorioAcompanhamento.getAluno().getNome());
		} finally {
			relatorioAcompanhamentoDaoImpl.sessionClose();
		}
	}

	public List<RelatorioAcompanhamento> getRelatorioAcompanhamentos() {

		relatorioAcompanhamentoDaoImpl.getSession();

		List<RelatorioAcompanhamento> lstRelAcomp = relatorioAcompanhamentoDaoImpl
				.findAll("protocolo");

		relatorioAcompanhamentoDaoImpl.sessionClose();

		return lstRelAcomp;
	}

	public RelatorioAcompanhamento getRelatorioAcompanhamento() {
		return relatorioAcompanhamento;
	}

	public void setRelatorioAcompanhamento(
			RelatorioAcompanhamento relatorioAcompanhamento) {
		this.relatorioAcompanhamento = relatorioAcompanhamento;
	}

	public List<RelatorioAcompanhamento> getFilteredRelatorioAcompanhamento() {
		return filteredRelatorioAcompanhamento;
	}

	public void setFilteredRelatorioAcompanhamento(
			List<RelatorioAcompanhamento> filteredRelatorioAcompanhamento) {
		this.filteredRelatorioAcompanhamento = filteredRelatorioAcompanhamento;
	}

}
