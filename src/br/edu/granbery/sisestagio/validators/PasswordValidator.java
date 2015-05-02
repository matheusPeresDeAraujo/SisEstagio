package br.edu.granbery.sisestagio.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validador respons�vel por garantir que a senha escolhida contenha n�meros,
 * caracteres especiais, letras e que tenha no m�nimo 7 e no m�ximo 22
 * caracteres
 */

@FacesValidator(value = "passwordValidator")
public class PasswordValidator implements Validator {

	int num = 0;
	int carac = 0;

	// Define um array de caracteres especiais
	char[] caracteresEspeciais = { '=', '|', '!', '@', '#', '$', '%', '^', '&',
			'*', '(', ')', '{', '}', '[', ']', ';', ':', '.', ',', '<', '>',
			'?', '~', '+', '-', '_', '\'', '"' };

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if (value == null) {
			return;
		}

		String password = (String) value;

		/*
		 * Monta um array de caracteres com o conte�do da senha e percorre-o
		 * contando quantos numeros a senha possui
		 */
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 48 && password.charAt(i) <= 57) {
				num++;
			}
		}
		System.out.println("Foram encontrados " + num + " numeros na senha!");

		/*
		 * Monta um array de caracteres com o conte�do da senha e percorre-o
		 * contando quantos caracteres especias a senha possui
		 */
		for (int i = 0; i < password.length(); i++) {
			for (int j = 0; j < caracteresEspeciais.length; j++) {
				if (password.charAt(i) == caracteresEspeciais[j]) {
					carac++;
				}
			}
		}
		System.out.println("Foram encontrados " + carac
				+ " caracteres especias na senha!");

		// Verifica se a senha n�o � vazia
		if (password.length() < 7) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"A senha deve ter no m�nimo 7 caracteres!", ""));
		} else if (password.length() >= 255) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"A senha deve ter no m�ximo 255 caracteres!!!", ""));
		} else if (!(carac > 0) || !(num > 0)) {
			throw new ValidatorException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "A senha deve conter n�meros, caracteres especiais e letras!",""));

			// Verifica se a senha possui pelo menos 7 caracteres e no m�ximo 22
		}
	}
}
