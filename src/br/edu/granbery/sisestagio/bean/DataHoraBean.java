package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "dataHoraBean")
@RequestScoped
public class DataHoraBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Date dataHora;
	private DateFormat dateFormat;

	public String getDataHora() {
		dateFormat = new SimpleDateFormat("EEEE, dd ' de ' MMMM ' de ' yyyy', ' HH:mm:ss");
		dataHora = new Date();
		return dateFormat.format(dataHora);
	}

	public String getData() {
		dateFormat = new SimpleDateFormat("EEEE, dd ' de ' MMMM ' de ' yyyy");
		dataHora = new Date();
		return dateFormat.format(dataHora);
	}
	
	public String getConverteData(Date data) {
		dateFormat = new SimpleDateFormat("EEEE, dd ' de ' MMMM ' de ' yyyy");
		return dateFormat.format(data);
	}

}
