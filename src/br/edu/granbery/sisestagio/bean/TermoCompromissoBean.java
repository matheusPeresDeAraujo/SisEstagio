package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.granbery.sisestagio.dao.TermoCompromissoDaoImpl;
import br.edu.granbery.sisestagio.model.TermoCompromisso;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.validators.DataValidator;
import br.edu.granbery.sisestagio.validators.ExclusaoValidator;
import br.edu.granbery.sisestagio.validators.ProtocoloValidator;

@ManagedBean(name = "termoCompromissoBean")
@RequestScoped
public class TermoCompromissoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private TermoCompromisso termoCompromisso;
	private TermoCompromissoDaoImpl termoCompromissoDaoImpl;
	private List<TermoCompromisso> filteredTermoCompromisso;
	private boolean mostrar = true;

	public TermoCompromissoBean() {
		this.termoCompromisso = new TermoCompromisso();
		this.termoCompromissoDaoImpl = new TermoCompromissoDaoImpl();
	}

	public void exibirTrouxeCtps() {
		if (termoCompromisso.getDeclaracaoDeTrabalho()) {
			mostrar = false;
		} else {
			mostrar = true;
		}
	}

	public void salvaTermoCompromisso() {
		boolean vReturn = false;
		try {
			termoCompromissoDaoImpl.getSession();

			DataValidator dataValidator = new DataValidator();

			vReturn = dataValidator.validateEndDate(
					termoCompromisso.getDataInicio(),
					termoCompromisso.getDataTermino());

			termoCompromissoDaoImpl.save(termoCompromisso);
			MessagesUtil.info("Termo de Compromisso para Aluno "
					+ termoCompromisso.getAluno().getNome()
					+ " salvo com sucesso.");
			this.termoCompromisso = new TermoCompromisso();
		} catch (Exception e) {
			if (vReturn) {
				MessagesUtil
						.error("Erro ao salvar Termo de Compromisso para Aluno "
								+ termoCompromisso.getAluno().getNome());
			} else {
				MessagesUtil
						.error("Data de Início precisa ser menor ou igual que Data de Termino.");
			}

		} finally {
			termoCompromissoDaoImpl.sessionClose();
		}

	}

	public void updateTermoCompromisso() {
		boolean vReturn = false;

		try {

			termoCompromissoDaoImpl.getSession();

			if (termoCompromissoDaoImpl.findFields("idTermoDeCompromisso",
					termoCompromisso.getIdTermoDeCompromisso(), "protocolo",
					termoCompromisso.getProtocolo()) == null) {
				ProtocoloValidator.getInstance().ProtocoloValidatorUpdate(
						termoCompromisso.getProtocolo());
			}

			DataValidator dataValidator = new DataValidator();

			vReturn = dataValidator.validateEndDate(
					termoCompromisso.getDataInicio(),
					termoCompromisso.getDataTermino());

			termoCompromissoDaoImpl.update(termoCompromisso);
			MessagesUtil.info("Dados do Termo de Compromisso para Aluno "
					+ termoCompromisso.getAluno().getNome()
					+ " alterado com sucesso.");
			this.termoCompromisso = new TermoCompromisso();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				if (vReturn) {
					MessagesUtil
							.error("Erro ao alterar os dados do Termo de Compromisso para Aluno "
									+ termoCompromisso.getAluno().getNome());
				} else {
					MessagesUtil
							.error("Data de Início precisa ser menor ou igual que Data de Termino.");
				}
			}
		} finally {
			termoCompromissoDaoImpl.sessionClose();
		}

	}

	public void excluir() {
		try {
			
			// Validar exclusão
			ExclusaoValidator.getInstance().exclusaoValidatorTermoCompromisso(
					termoCompromisso);
			
			termoCompromissoDaoImpl.getSession();			
			
			termoCompromissoDaoImpl.remove(termoCompromisso);
			MessagesUtil.info("Termo de Compromisso do Aluno "
					+ termoCompromisso.getAluno().getNome()
					+ " excluindo com sucesso.");
			this.termoCompromisso = new TermoCompromisso();
			this.filteredTermoCompromisso = termoCompromissoDaoImpl
					.findAll("protocolo");
			termoCompromissoDaoImpl.sessionClose();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil
						.error("Erro ao excluir Termo de Compromisso do Aluno "
								+ termoCompromisso.getAluno().getNome());
				termoCompromissoDaoImpl.sessionClose();
			}
		}
	}

	public List<TermoCompromisso> getTermoCompromissos() {
		termoCompromissoDaoImpl.getSession();

		List<TermoCompromisso> termoCompromissos = termoCompromissoDaoImpl
				.findAll("protocolo");

		termoCompromissoDaoImpl.sessionClose();

		return termoCompromissos;
	}

	public TermoCompromisso getTermoCompromisso() {
		return termoCompromisso;
	}

	public void setTermoCompromisso(TermoCompromisso termoCompromisso) {
		this.termoCompromisso = termoCompromisso;
	}

	public List<TermoCompromisso> getFilteredTermoCompromisso() {
		return filteredTermoCompromisso;
	}

	public void setFilteredTermoCompromisso(
			List<TermoCompromisso> filteredTermoCompromisso) {
		this.filteredTermoCompromisso = filteredTermoCompromisso;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

}
