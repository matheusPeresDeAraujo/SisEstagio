package br.edu.granbery.sisestagio.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.granbery.sisestagio.dao.AlunoDaoImpl;
import br.edu.granbery.sisestagio.model.Aluno;

@FacesValidator(value = "matriculaValidator")
public class MatriculaValidator implements Validator {

	private static MatriculaValidator instance = new MatriculaValidator();

	public static MatriculaValidator getInstance() {
		return instance;
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object valorTela)
			throws ValidatorException {

		String matricula = (String) valorTela;

		if (!isMatriculaValidator(matricula)) {
			FacesMessage message = new FacesMessage();
			message.setDetail("N° de Matrícula: Já definido para um outro Aluno.");
			message.setSummary("N° de Matrícula: Já definido para um outro Aluno.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

	private static boolean isMatriculaValidator(String matricula) {

		AlunoDaoImpl alunoDaoImpl = new AlunoDaoImpl();
		
		alunoDaoImpl.getSession();
		
		Aluno aluno = alunoDaoImpl.findField("matricula", matricula);
		
		alunoDaoImpl.sessionClose();

		if ( aluno != null) {
			return false;
		}

		return true;
	}

	public void MatriculaValidatorUpdate(String matricula, Integer id)
			throws Exception {
		
		AlunoDaoImpl alunoDaoImpl = new AlunoDaoImpl();
		
		alunoDaoImpl.getSession();
		
		Aluno aluno = alunoDaoImpl.findFields("idAluno", id, "matricula", matricula);
		
		alunoDaoImpl.sessionClose();

		if (aluno == null) {
			if (!isMatriculaValidator(matricula)) {
				throw new Exception(
						"N° de Matrícula: Já definido para um outro Aluno.");
			}
		}
	}

}
