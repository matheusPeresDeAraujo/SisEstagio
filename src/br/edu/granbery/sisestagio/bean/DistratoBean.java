package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.granbery.sisestagio.dao.DistratoDaoImpl;
import br.edu.granbery.sisestagio.model.Distrato;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.validators.ProtocoloValidator;

@ManagedBean(name = "distratoBean")
@RequestScoped
public class DistratoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Distrato distrato;
	private DistratoDaoImpl distratoDaoImpl;
	private List<Distrato> filteredDistrato;

	public DistratoBean() {
		this.distrato = new Distrato();
		this.distratoDaoImpl = new DistratoDaoImpl();
	}

	public void salvaDistrato() {
		try {
			distratoDaoImpl.getSession();

			distratoDaoImpl.save(distrato);
			MessagesUtil.info("Distrato para Aluno "
					+ distrato.getTermoDeCompromisso().getAluno().getNome()
					+ " salvo com sucesso.");
			this.distrato = new Distrato();
		} catch (Exception e) {
			MessagesUtil.error("Erro ao salvar Distrato para Aluno "
					+ distrato.getTermoDeCompromisso().getAluno().getNome()
					+ ": Já tem Distrato cadastro no banco de dados!");
		} finally {
			distratoDaoImpl.sessionClose();
		}

	}

	public void updateDistrato() {
		try {

			distratoDaoImpl.getSession();

			if (distratoDaoImpl.findFields("idDistrato",
					distrato.getIdDistrato(), "protocolo",
					distrato.getProtocolo()) == null) {
				ProtocoloValidator.getInstance().ProtocoloValidatorUpdate(
						distrato.getProtocolo());
			}

			distratoDaoImpl.update(distrato);
			MessagesUtil.info("Dados do Distrato para Aluno "
					+ distrato.getTermoDeCompromisso().getAluno().getNome()
					+ " alterado com sucesso.");
			this.distrato = new Distrato();
		} catch (Exception e) {

			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil
						.error("Erro ao alterar os dados do Distrato para Aluno "
								+ distrato.getTermoDeCompromisso().getAluno()
										.getNome()
								+ ": Já tem Distratocadastro no banco de dados!");
			}

		} finally {
			distratoDaoImpl.sessionClose();
		}

	}

	public void excluir() {
		try {

			distratoDaoImpl.getSession();

			distratoDaoImpl.remove(distrato);
			MessagesUtil.info("Distrato do Aluno "
					+ distrato.getTermoDeCompromisso().getAluno().getNome()
					+ " excluindo com sucesso.");
			this.distrato = new Distrato();
			this.filteredDistrato = distratoDaoImpl.findAll("protocolo");
		} catch (Exception e) {
			MessagesUtil.error("Erro ao excluir Distrato do Aluno "
					+ distrato.getTermoDeCompromisso().getAluno().getNome());
		} finally {
			distratoDaoImpl.sessionClose();
		}
	}

	public List<Distrato> getDistratos() {
		distratoDaoImpl.getSession();

		List<Distrato> distratos = distratoDaoImpl.findAll("protocolo");

		distratoDaoImpl.sessionClose();
		return distratos;
	}

	public Distrato getDistrato() {
		return distrato;
	}

	public void setDistrato(Distrato distrato) {
		this.distrato = distrato;
	}

	public List<Distrato> getFilteredDistrato() {
		return filteredDistrato;
	}

	public void setFilteredDistrato(List<Distrato> filteredDistrato) {
		this.filteredDistrato = filteredDistrato;
	}

}