package br.edu.granbery.sisestagio.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.granbery.sisestagio.dao.UsuarioDaoImpl;

/**
 * Validador responsável por garantir que não já e-mail duplicado na tabela
 * usuário do banco de dados
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
			message.setDetail("E-mail: Já definido para um outro usuário.");
			message.setSummary("E-mail: Já definido para um outro usuário.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		usuarioDaoImpl.sessionClose();
	}
}
