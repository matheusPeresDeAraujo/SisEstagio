package br.edu.granbery.sisestagio.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.granbery.sisestagio.dao.CurriculoDaoImpl;
import br.edu.granbery.sisestagio.model.Curriculo;

@FacesValidator(value = "numeroCurriculoDisponibilidadeValidator")
public class NumeroCurriculoDisponibilidadeValidator implements Validator {
	
	private static NumeroCurriculoDisponibilidadeValidator instance = new NumeroCurriculoDisponibilidadeValidator();

	public static NumeroCurriculoDisponibilidadeValidator getInstance() {
		return instance;
	}

	@Override
	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object object) throws ValidatorException {

		Integer enteredNumeroCurriculo = (Integer) object;
		CurriculoDaoImpl curriculoDaoImpl = new CurriculoDaoImpl();
		
		curriculoDaoImpl.getSession();

		if (curriculoDaoImpl.numeroCurriculoDisponibilidade(enteredNumeroCurriculo)) {
			curriculoDaoImpl.sessionClose();
			FacesMessage message = new FacesMessage();
			message.setDetail("Número: Já definido para um outro Currículo.");
			message.setSummary("Número: Já definido para um outro Currículo.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		
		curriculoDaoImpl.sessionClose();
	}
	
	public void CurriculoValidatorUpdate(Integer numero, Integer id)
			throws Exception {
		CurriculoDaoImpl curriculoDaoImpl = new CurriculoDaoImpl();
		
		curriculoDaoImpl.getSession();
		
		Curriculo curriculo = curriculoDaoImpl.findFields("idCurriculo", id, "numero", numero);
		
		curriculoDaoImpl.sessionClose();

		if ( curriculo == null) {
			if (!isCurriculoValidator(numero)) {
				throw new Exception("Número: Já definido para um outro Currículo.");
			}
		}
	}
	
	private static boolean isCurriculoValidator(Integer numero) {

		CurriculoDaoImpl curriculoDaoImpl = new CurriculoDaoImpl();
		
		curriculoDaoImpl.getSession();
		
		Curriculo curriculo = curriculoDaoImpl.findField("numero", numero);
		
		curriculoDaoImpl.sessionClose();		

		if ( curriculo != null) {
			return false;
		}

		return true;
	}

}
