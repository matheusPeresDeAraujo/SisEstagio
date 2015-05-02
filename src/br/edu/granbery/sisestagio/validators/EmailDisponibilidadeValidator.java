package br.edu.granbery.sisestagio.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.granbery.sisestagio.dao.UsuarioDaoImpl;

/**
 * Validador respons�vel por garantir que n�o j� e-mail duplicado na tabela
 * usu�rio do banco de dados
 */

@FacesValidator(value = "emailDisponibilidadeValidator")
public class EmailDisponibilidadeValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		String enteredEmail = (String) object;
		UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
		
		usuarioDaoImpl.getSession();

		if (usuarioDaoImpl.emailDisponibilidade(enteredEmail)) {
			
			usuarioDaoImpl.sessionClose();			
			FacesMessage message = new FacesMessage();
			message.setDetail("E-mail: J� definido para um outro usu�rio.");
			message.setSummary("E-mail: J� definido para um outro usu�rio.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		usuarioDaoImpl.sessionClose();
	}
}
