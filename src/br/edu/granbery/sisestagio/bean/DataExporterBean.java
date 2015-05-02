package br.edu.granbery.sisestagio.bean;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;


@ManagedBean(name = "dataExporterBean")
@RequestScoped
public class DataExporterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public void preProcessPDFHorasFalta(Object document) throws IOException,
			BadElementException, DocumentException {
		
        //cria o documento
        Document pdf = (Document) document;

        pdf.open();
        
        pdf.setPageSize(PageSize.A4);

        //aqui pega o contexto para formar a url da imagem
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String logo = servletContext.getRealPath("") + File.separator + "resources/img" + File.separator + "logo_faculdade.png";


        //cria a imagem e passando a url
        Image image = Image.getInstance(logo);

        //alinha ao centro
        image.setAlignment(Image.ALIGN_CENTER);

        //adciona a img ao pdf
        pdf.add(image);

        //data
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Paragraph p = new Paragraph("Data de Emissão: " + formato.format(new Date()));
        p.setAlignment("right");
        pdf.add(p);

        //pular uma linha
        p = new Paragraph("             ");
        p.setAlignment("center");
        pdf.add(p);

        //Título
        p = new Paragraph("Relatório de Total de Horas para Termino do Estágio por aluno");
        p.setAlignment("center");
        pdf.add(p);

        //pular uma linha
        p = new Paragraph("             ");
        p.setAlignment("center");
        pdf.add(p);
        
	}

	public void preProcessPDFHorasToAluno(Object document) throws IOException,
			BadElementException, DocumentException {
		
        //cria o documento
        Document pdf = (Document) document;

        pdf.open();
        
        pdf.setPageSize(PageSize.A4);

        //aqui pega o contexto para formar a url da imagem
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String logo = servletContext.getRealPath("") + File.separator + "resources/img" + File.separator + "logo_faculdade.png";


        //cria a imagem e passando a url
        Image image = Image.getInstance(logo);

        //alinha ao centro
        image.setAlignment(Image.ALIGN_CENTER);

        //adciona a img ao pdf
        pdf.add(image);

        //data
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Paragraph p = new Paragraph("Data de Emissão: " + formato.format(new Date()));
        p.setAlignment("right");
        pdf.add(p);

        //pular uma linha
        p = new Paragraph("             ");
        p.setAlignment("center");
        pdf.add(p);

        //Título
        p = new Paragraph("Relatório de Total de Horas Realizadas por aluno");
        p.setAlignment("center");
        pdf.add(p);

        //pular uma linha
        p = new Paragraph("             ");
        p.setAlignment("center");
        pdf.add(p);

	}
	
	public void preProcessPDFRelTotalAluno(Object document) throws IOException,
			BadElementException, DocumentException {
		
        //cria o documento
        Document pdf = (Document) document;

        pdf.open();
        
        pdf.setPageSize(PageSize.A4);

        //aqui pega o contexto para formar a url da imagem
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String logo = servletContext.getRealPath("") + File.separator + "resources/img" + File.separator + "logo_faculdade.png";


        //cria a imagem e passando a url
        Image image = Image.getInstance(logo);

        //alinha ao centro
        image.setAlignment(Image.ALIGN_CENTER);

        //adciona a img ao pdf
        pdf.add(image);

        //data
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Paragraph p = new Paragraph("Data de Emissão: " + formato.format(new Date()));
        p.setAlignment("right");
        pdf.add(p);

        //pular uma linha
        p = new Paragraph("             ");
        p.setAlignment("center");
        pdf.add(p);

        //Título
        p = new Paragraph("Relatório Individual do Total de Horas de Estágio");
        p.setAlignment("center");
        pdf.add(p);

        //pular uma linha
        p = new Paragraph("             ");
        p.setAlignment("center");
        pdf.add(p);

	}

}
