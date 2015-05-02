package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.granbery.sisestagio.dao.TermoAditivoDaoImpl;
import br.edu.granbery.sisestagio.model.TermoAditivo;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.validators.DataValidator;
import br.edu.granbery.sisestagio.validators.ProtocoloValidator;

@ManagedBean(name = "termoAditivoBean")
@RequestScoped
public class TermoAditivoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private TermoAditivo termoAditivo;
	private TermoAditivoDaoImpl termoAditivoDaoImpl;
	private List<TermoAditivo> filteredTermoAditivo;

	public TermoAditivoBean() {
		this.termoAditivo = new TermoAditivo();
		this.termoAditivoDaoImpl = new TermoAditivoDaoImpl();
	}

	public void salvaTermoAditivo() {
		boolean vReturn = false;
		try {
			termoAditivoDaoImpl.getSession();

			DataValidator dataValidator = new DataValidator();

			vReturn = dataValidator
					.validateEndDate(termoAditivo.getDataInicio(),
							termoAditivo.getDataTermino());

			termoAditivoDaoImpl.save(termoAditivo);

			MessagesUtil.info("Termo Aditivo para Aluno "
					+ termoAditivo.getTermoDeCompromisso().getAluno().getNome()
					+ " salvo com sucesso.");

			this.termoAditivo = new TermoAditivo();
		} catch (Exception e) {
			if (vReturn) {
				MessagesUtil.error("Erro ao salvar Termo Aditivo para Aluno "
						+ termoAditivo.getTermoDeCompromisso().getAluno()
								.getNome());
			} else {
				MessagesUtil
						.error("Data de Início precisa ser menor ou igual que Data de Termino.");
			}

		} finally {
			termoAditivoDaoImpl.sessionClose();
		}

	}

	public void updateTermoAditivo() {
		boolean vReturn = false;

		try {
			termoAditivoDaoImpl.getSession();

			if (termoAditivoDaoImpl.findFields("idTermoAditivo",
					termoAditivo.getIdTermoAditivo(), "protocolo",
					termoAditivo.getProtocolo()) == null) {
				ProtocoloValidator.getInstance().ProtocoloValidatorUpdate(
						termoAditivo.getProtocolo());
			}

			DataValidator dataValidator = new DataValidator();

			vReturn = dataValidator
					.validateEndDate(termoAditivo.getDataInicio(),
							termoAditivo.getDataTermino());

			termoAditivoDaoImpl.update(termoAditivo);

			MessagesUtil.info("Dados do Termo Aditivo para Aluno "
					+ termoAditivo.getTermoDeCompromisso().getAluno().getNome()
					+ " alterado com sucesso.");

			this.termoAditivo = new TermoAditivo();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				if (vReturn) {
					MessagesUtil
							.error("Erro ao alterar os dados do Termo Aditivo para Aluno "
									+ termoAditivo.getTermoDeCompromisso()
											.getAluno().getNome());
				} else {
					MessagesUtil
							.error("Data de Início precisa ser menor ou igual que Data de Termino.");
				}
			}
		} finally {
			termoAditivoDaoImpl.sessionClose();
		}

	}

	public void excluir() {
		try {
			termoAditivoDaoImpl.getSession();
			
			
			termoAditivoDaoImpl.remove(termoAditivo);

			MessagesUtil.info("Termo Aditivo do Aluno "
					+ termoAditivo.getTermoDeCompromisso().getAluno().getNome()
					+ " excluindo com sucesso.");

			this.termoAditivo = new TermoAditivo();

			this.filteredTermoAditivo = termoAditivoDaoImpl
					.findAll("protocolo");

		} catch (Exception e) {
			MessagesUtil
					.error("Erro ao excluir Termo Aditivo do Aluno "
							+ termoAditivo.getTermoDeCompromisso().getAluno()
									.getNome());
		} finally {
			termoAditivoDaoImpl.sessionClose();
		}
	}

	public List<TermoAditivo> getTermoAditivos() {
		termoAditivoDaoImpl.getSession();

		List<TermoAditivo> termoAditivos = termoAditivoDaoImpl
				.findAll("protocolo");

		termoAditivoDaoImpl.sessionClose();

		return termoAditivos;
	}

	public TermoAditivo getTermoAditivo() {
		return termoAditivo;
	}

	public void setTermoAditivo(TermoAditivo termoAditivo) {
		this.termoAditivo = termoAditivo;
	}

	public List<TermoAditivo> getFilteredTermoAditivo() {
		return filteredTermoAditivo;
	}

	public void setFilteredTermoAditivo(List<TermoAditivo> filteredTermoAditivo) {
		this.filteredTermoAditivo = filteredTermoAditivo;
	}

}
