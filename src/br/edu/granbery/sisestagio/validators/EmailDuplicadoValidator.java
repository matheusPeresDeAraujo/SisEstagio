package br.edu.granbery.sisestagio.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.granbery.sisestagio.dao.AlunoDaoImpl;

/**
 * Validador respons�vel por garantir que n�o j� e-mail duplicado na tabela
 * aluno do banco de dados
 */

@FacesValidator(value = "emailDuplicadoValidator")
public class EmailDuplicadoValidator implements Validator {
	
	private static EmailDuplicadoValidator instance = new EmailDuplicadoValidator();

	public static EmailDuplicadoValidator getInstance() {
		return instance;
	}

	@Override
	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		String enteredEmail = (String) object;
		AlunoDaoImpl alunoDaoImpl = new AlunoDaoImpl();

		alunoDaoImpl.getSession();
		
		if (alunoDaoImpl.findField("email", enteredEmail) != null) {
			alunoDaoImpl.sessionClose();
			FacesMessage message = new FacesMessage();
			message.setDetail("E-mail: J� definido para um outro aluno.");
			message.setSummary("E-mail: J� definido para um outro aluno.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		
		alunoDaoImpl.sessionClose();

	}

	public void validateEmailToUpdate(Integer id, String email) {
		AlunoDaoImpl alunoDaoImpl = new AlunoDaoImpl();

		alunoDaoImpl.getSession();
		
		if (alunoDaoImpl.findFields("idAluno", id, "email", email) == null) {
			if (alunoDaoImpl.findField("email", email) != null) {
				alunoDaoImpl.sessionClose();
				FacesMessage message = new FacesMessage();
				message.setDetail("E-mail: J� definido para um outro aluno.");
				message.setSummary("E-mail: J� definido para um outro aluno.");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
		alunoDaoImpl.sessionClose();
	}
}
