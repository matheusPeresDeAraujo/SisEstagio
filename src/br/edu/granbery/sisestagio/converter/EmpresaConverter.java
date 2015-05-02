package br.edu.granbery.sisestagio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.granbery.sisestagio.dao.EmpresaDaoImpl;
import br.edu.granbery.sisestagio.model.Empresa;

@FacesConverter(forClass = Empresa.class, value = "empresaConverter")
public class EmpresaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String value) {
		if ((value == null) || (value.length() == 0)) {
			return null;
		}
		EmpresaDaoImpl dao = new EmpresaDaoImpl();
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

		if (value instanceof Empresa) {
			Empresa o = (Empresa) value;
			return getStringID(o.getIdEmpresa());
		} else {
			throw new IllegalArgumentException("objeto " + value
					+ " possui o tipo " + value.getClass().getName()
					+ "; tipo esperado: ");
		}
	}
}