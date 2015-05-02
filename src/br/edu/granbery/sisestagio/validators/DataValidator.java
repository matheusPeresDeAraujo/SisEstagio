package br.edu.granbery.sisestagio.validators;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "dataValidator")
public class DataValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object valorTela)
			throws ValidatorException {

		Date data = (Date) valorTela;
		
		if (!isDataValida(data)) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Data Inválido: Data precisa ser menor ou igual que a data atual.");
			message.setSummary("Data Inválido: Data precisa ser menor ou igual que a data atual.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

	public static boolean isDataValida(Date data) {

		SimpleDateFormat sdfAno = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
		SimpleDateFormat sdfDia = new SimpleDateFormat("dd");
		Calendar cal = Calendar.getInstance();
		Calendar currentcal = Calendar.getInstance();

		cal.set(Integer.parseInt(sdfAno.format(data)),
				Integer.parseInt(sdfMes.format(data)) - 1,
				Integer.parseInt(sdfDia.format(data)));

		currentcal.set(currentcal.get(Calendar.YEAR),
				currentcal.get(Calendar.MONTH),
				currentcal.get(Calendar.DAY_OF_MONTH));

		// Verifica se A data atual é menor que a data digitada pelo usuário
		if (cal.after(currentcal)) {
			return false;
		}

		return true;
	}

	public boolean validateEndDate(Date dataInit, Date dataFim)
			throws Exception {

		if (dataInit.after(dataFim)) {
			throw new Exception();
		}
		return true;
	}

}
