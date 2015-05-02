/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.granbery.sisestagio.converter;

import br.edu.granbery.sisestagio.dao.CurriculoDaoImpl;
import br.edu.granbery.sisestagio.model.Curriculo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * 
 * @author knupp
 */
@FacesConverter(forClass = Curriculo.class, value = "curriculoConverter")
public class CurriculoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) {
		if ((value == null) || (value.length() == 0)) {
			return null;
		}
		CurriculoDaoImpl dao = new CurriculoDaoImpl();
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

		if (value instanceof Curriculo) {
			Curriculo o = (Curriculo) value;
			return getStringID(o.getIdCurriculo());
		} else {
			throw new IllegalArgumentException("objeto " + value
					+ " possui o tipo " + value.getClass().getName()
					+ "; tipo esperado: ");
		}
	}
}
