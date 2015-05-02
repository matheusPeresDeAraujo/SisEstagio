package br.edu.granbery.sisestagio.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.granbery.sisestagio.dao.DistratoDaoImpl;
import br.edu.granbery.sisestagio.dao.ProjetoEstagioDaoImpl;
import br.edu.granbery.sisestagio.dao.ProjetoFinalDaoImpl;
import br.edu.granbery.sisestagio.dao.RelatorioAcompanhamentoDaoImpl;
import br.edu.granbery.sisestagio.dao.RelatorioMensalDaoImpl;
import br.edu.granbery.sisestagio.dao.TermoAditivoDaoImpl;
import br.edu.granbery.sisestagio.dao.TermoCompromissoDaoImpl;
import br.edu.granbery.sisestagio.model.Distrato;
import br.edu.granbery.sisestagio.model.ProjetoEstagio;
import br.edu.granbery.sisestagio.model.ProjetoFinal;
import br.edu.granbery.sisestagio.model.RelatorioAcompanhamento;
import br.edu.granbery.sisestagio.model.RelatorioMensal;
import br.edu.granbery.sisestagio.model.TermoAditivo;
import br.edu.granbery.sisestagio.model.TermoCompromisso;

@FacesValidator(value = "protocoloValidator")
public class ProtocoloValidator implements Validator {

	private static ProtocoloValidator instance = new ProtocoloValidator();

	public static ProtocoloValidator getInstance() {
		return instance;
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object valorTela)
			throws ValidatorException {

		String protocolo = (String) valorTela;

		if (!isProtocoloValidator(protocolo)) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Protocolo: Já definido para um outro Documento.");
			message.setSummary("Protocolo: Já definido para um outro Documento.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

	private static boolean isProtocoloValidator(String protocolo) {

		DistratoDaoImpl distratoImpl = new DistratoDaoImpl();

		distratoImpl.getSession();
		Distrato distrato = distratoImpl.findField("protocolo", protocolo);
		distratoImpl.sessionClose();

		if (distrato != null) {
			return false;
		}

		ProjetoEstagioDaoImpl projetoEstagioDaoImpl = new ProjetoEstagioDaoImpl();

		projetoEstagioDaoImpl.getSession();
		ProjetoEstagio projetoEstagio = projetoEstagioDaoImpl.findField(
				"protocolo", protocolo);
		projetoEstagioDaoImpl.sessionClose();

		if (projetoEstagio != null) {
			return false;
		}

		ProjetoFinalDaoImpl projetoFinalImpl = new ProjetoFinalDaoImpl();

		projetoFinalImpl.getSession();
		ProjetoFinal projetoFinal = projetoFinalImpl.findField("protocolo",
				protocolo);
		projetoFinalImpl.sessionClose();

		if (projetoFinal != null) {
			return false;
		}

		RelatorioAcompanhamentoDaoImpl relatorioAcompanhamentoDaoImpl = new RelatorioAcompanhamentoDaoImpl();

		relatorioAcompanhamentoDaoImpl.getSession();
		RelatorioAcompanhamento relAcompanhamento = relatorioAcompanhamentoDaoImpl
				.findField("protocolo", protocolo);
		relatorioAcompanhamentoDaoImpl.sessionClose();

		if (relAcompanhamento != null) {
			return false;
		}

		RelatorioMensalDaoImpl relatorioMensalImpl = new RelatorioMensalDaoImpl();

		relatorioMensalImpl.getSession();
		RelatorioMensal relatorioMensal = relatorioMensalImpl.findField(
				"protocolo", protocolo);
		relatorioMensalImpl.sessionClose();

		if (relatorioMensal != null) {
			return false;
		}

		TermoAditivoDaoImpl termoAditivoDaoImpl = new TermoAditivoDaoImpl();

		termoAditivoDaoImpl.getSession();
		TermoAditivo termoAditivo = termoAditivoDaoImpl.findField("protocolo",
				protocolo);
		termoAditivoDaoImpl.sessionClose();

		if (termoAditivo != null) {
			return false;
		}

		TermoCompromissoDaoImpl termoCompromissoDaoImpl = new TermoCompromissoDaoImpl();

		termoCompromissoDaoImpl.getSession();
		TermoCompromisso termoCompromisso = termoCompromissoDaoImpl.findField(
				"protocolo", protocolo);
		termoCompromissoDaoImpl.sessionClose();

		if (termoCompromisso != null) {
			return false;
		}

		return true;
	}

	public void ProtocoloValidatorUpdate(String protocolo) throws Exception {
		if (!isProtocoloValidator(protocolo)) {
			throw new Exception(
					"Protocolo: Já definido para um outro Documento.");
		}
	}

}
