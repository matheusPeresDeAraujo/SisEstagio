package br.edu.granbery.sisestagio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.granbery.sisestagio.dao.TermoCompromissoDaoImpl;
import br.edu.granbery.sisestagio.model.TermoCompromisso;

@FacesConverter(forClass = TermoCompromisso.class, value = "termoCompromissoConverter")
public class TermoCompromissoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) {
		if ((value == null) || (value.length() == 0)) {
			return null;
		}
		TermoCompromissoDaoImpl dao = new TermoCompromissoDaoImpl();
		return dao.find(getID(value));
	}

	Integer getID(String value) {
		Integer id;
		id = Integer.valueOf(value);
		return id;
	}

	String getStringID(Integer value) {
		StringBuilder sb = new StringBuilder();
		sb.append(value);
		return sb.toString();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof TermoCompromisso) {
			TermoCompromisso o = (TermoCompromisso) value;
			return getStringID(o.getIdTermoDeCompromisso());
		} else {
			throw new IllegalArgumentException("objeto " + value
					+ " possui o tipo " + value.getClass().getName()
					+ "; tipo esperado: ");
		}
	}
}
