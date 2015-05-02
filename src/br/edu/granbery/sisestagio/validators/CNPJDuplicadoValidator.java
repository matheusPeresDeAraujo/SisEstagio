package br.edu.granbery.sisestagio.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.granbery.sisestagio.dao.EmpresaDaoImpl;
import br.edu.granbery.sisestagio.model.Empresa;

@FacesValidator(value = "cnpjDuplicadoValidator")
public class CNPJDuplicadoValidator implements Validator {

	private static CNPJDuplicadoValidator instance = new CNPJDuplicadoValidator();

	public static CNPJDuplicadoValidator getInstance() {
		return instance;
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object valorTela)
			throws ValidatorException {

		Long cnpj = (Long) valorTela;

		if (!isCnpjValidator(cnpj)) {
			FacesMessage message = new FacesMessage();
			message.setDetail("CNPJ: Já definido para um outra Empresa.");
			message.setSummary("CNPJ: Já definido para um outra Empresa.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

	private static boolean isCnpjValidator(Long cnpj) {

		EmpresaDaoImpl empresaDaoImpl = new EmpresaDaoImpl();

		empresaDaoImpl.getSession();

		Empresa empresa = empresaDaoImpl.findField("cnpj", cnpj);

		empresaDaoImpl.sessionClose();

		if (empresa != null) {
			return false;
		}

		return true;
	}

	public void cnpjValidatorUpdate(Long cnpj, Integer id) throws Exception {

		EmpresaDaoImpl empresaDaoImpl = new EmpresaDaoImpl();

		empresaDaoImpl.getSession();

		Empresa empresa = empresaDaoImpl.findFields("idEmpresa", id, "cnpj",cnpj);

		empresaDaoImpl.sessionClose();

		if (empresa == null) {
			if (!isCnpjValidator(cnpj)) {
				throw new Exception("CNPJ: Já definido para um outra Empresa.");
			}
		}
	}

}
