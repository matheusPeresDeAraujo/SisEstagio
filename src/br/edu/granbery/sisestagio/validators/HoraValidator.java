package br.edu.granbery.sisestagio.validators;

public class HoraValidator {

	private static HoraValidator instance = new HoraValidator();

	public static HoraValidator getInstance() {
		return instance;
	}

	public void validateEndHora(double horasRegistradas,
			double horasAproveitadas) throws Exception {

		if (horasAproveitadas > horasRegistradas) {
			throw new Exception("O valor do campo Horas Aproveitadas precisa"
									+ " ser menor ou igual ao valor do campo Horas Registradas. ");
		}

	}

}
