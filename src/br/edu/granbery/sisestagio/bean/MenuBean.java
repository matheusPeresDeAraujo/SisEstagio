package br.edu.granbery.sisestagio.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

@ManagedBean(name = "menuBean")
@RequestScoped
public class MenuBean {

	private MenuModel model;

	public MenuBean() {
		model = new DefaultMenuModel();
		
		MenuItem item = new MenuItem();
		Submenu submenu = new Submenu();
		Submenu submenuChildren = new Submenu();

		// MENU ALUNO
		submenu = new Submenu();
		submenu.setIcon("ui-icon ui-icon-circle-triangle-e");
		submenu.setLabel("Aluno");
			item = new MenuItem();
			item.setIcon("ui-icon ui-icon-document");
			item.setValue("Novo Aluno");
			item.setUrl("/admin/aluno/incluir.xhtml");
			submenu.getChildren().add(item);
			
			item = new MenuItem();
			item.setIcon("ui-icon ui-icon-folder-open");
			item.setValue("Listar Alunos");
			item.setUrl("/admin/aluno/aluno.xhtml");
			submenu.getChildren().add(item);
			
				// SUBMENU CURRICULO
				submenuChildren = new Submenu();
				submenuChildren.setIcon("ui-icon ui-icon-circle-triangle-e");
				submenuChildren.setLabel("Curr�culo");
					
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-document");
					item.setValue("Novo Curr�culo");
					item.setUrl("/admin/curriculo/incluir.xhtml");
					submenuChildren.getChildren().add(item);
	
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-folder-open");
					item.setValue("Listar Curriculos");
					item.setUrl("/admin/curriculo/curriculo.xhtml");
					submenuChildren.getChildren().add(item);
					
				submenu.getChildren().add(submenuChildren);
				
				// SUBMENU Distrato
				submenuChildren = new Submenu();
				submenuChildren.setIcon("ui-icon ui-icon-circle-triangle-e");
				submenuChildren.setLabel("Distrato");
					
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-document");
					item.setValue("Novo Distrato");
					item.setUrl("/admin/distrato/incluir.xhtml");
					submenuChildren.getChildren().add(item);
	
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-folder-open");
					item.setValue("Listar distratos");
					item.setUrl("/admin/distrato/distrato.xhtml");
					submenuChildren.getChildren().add(item);
					
				submenu.getChildren().add(submenuChildren);
				
				// SUBMENU PROJETO DE ESTAGIOS
				submenuChildren = new Submenu();
				submenuChildren.setIcon("ui-icon ui-icon-circle-triangle-e");
				submenuChildren.setLabel("Projeto de Est�gio");
					
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-document");
					item.setValue("Novo Projeto de Est�gio");
					item.setUrl("/admin/projetoEstagio/incluir.xhtml");
					submenuChildren.getChildren().add(item);
	
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-folder-open");
					item.setValue("Listar Projetos de Est�gio");
					item.setUrl("/admin/projetoEstagio/projetoEstagio.xhtml");
					submenuChildren.getChildren().add(item);
					
				submenu.getChildren().add(submenuChildren);
				
				// SUBMENU PROJETO Final
				submenuChildren = new Submenu();
				submenuChildren.setIcon("ui-icon ui-icon-circle-triangle-e");
				submenuChildren.setLabel("Projeto Final");
					
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-document");
					item.setValue("Novo Projeto Final");
					item.setUrl("/admin/projetoFinal/incluir.xhtml");
					submenuChildren.getChildren().add(item);
	
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-folder-open");
					item.setValue("Listar Projetos Final");
					item.setUrl("/admin/projetoFinal/projetoFinal.xhtml");
					submenuChildren.getChildren().add(item);
					
				submenu.getChildren().add(submenuChildren);
				
				// SUBMENU Relat�rio de Acompanhamento
				submenuChildren = new Submenu();
				submenuChildren.setIcon("ui-icon ui-icon-circle-triangle-e");
				submenuChildren.setLabel("Relat�rio de Acompanhamento");
					
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-document");
					item.setValue("Novo Relat�rio de Acompanhamento");
					item.setUrl("/admin/relatorioAcompanhamento/incluir.xhtml");
					submenuChildren.getChildren().add(item);
	
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-folder-open");
					item.setValue("Listar Relat�rios de Acompanhamento");
					item.setUrl("/admin/relatorioAcompanhamento/relatorioAcompanhamento.xhtml");
					submenuChildren.getChildren().add(item);
					
				submenu.getChildren().add(submenuChildren);
				
				// SUBMENU Relat�rio de Acompanhamento
				submenuChildren = new Submenu();
				submenuChildren.setIcon("ui-icon ui-icon-circle-triangle-e");
				submenuChildren.setLabel("Termo Aditivo");
					
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-document");
					item.setValue("Novo Termo Aditivo");
					item.setUrl("/admin/termoAditivo/incluir.xhtml");
					submenuChildren.getChildren().add(item);
	
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-folder-open");
					item.setValue("Listar Termos Aditivo");
					item.setUrl("/admin/termoAditivo/termoAditivo.xhtml");
					submenuChildren.getChildren().add(item);
					
				submenu.getChildren().add(submenuChildren);
				
				// SUBMENU Relat�rio de Acompanhamento
				submenuChildren = new Submenu();
				submenuChildren.setIcon("ui-icon ui-icon-circle-triangle-e");
				submenuChildren.setLabel("Termo de Compromisso");
					
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-document");
					item.setValue("Novo Termo de Compromisso");
					item.setUrl("/admin/termoCompromisso/incluir.xhtml");
					submenuChildren.getChildren().add(item);
	
					item = new MenuItem();
					item.setIcon("ui-icon ui-icon-folder-open");
					item.setValue("Listar Termos de Compromisso");
					item.setUrl("/admin/termoCompromisso/termoCompromisso.xhtml");
					submenuChildren.getChildren().add(item);
					
				submenu.getChildren().add(submenuChildren);
				
				
				
		model.addSubmenu(submenu);
		
		// MENU EMPRESA
		submenu = new Submenu();
		submenu.setIcon("ui-icon ui-icon-circle-triangle-e");
		submenu.setLabel("Empresa");
		
			item = new MenuItem();
			item.setIcon("ui-icon ui-icon-document");
			item.setValue("Nova Empresa");
			item.setUrl("/admin/empresa/incluir.xhtml");
			submenu.getChildren().add(item);
	
			item = new MenuItem();
			item.setIcon("ui-icon ui-icon-folder-open");
			item.setValue("Listar Empresas");
			item.setUrl("/admin/empresa/empresa.xhtml");
			submenu.getChildren().add(item);
			
		model.addSubmenu(submenu);
		
		
		// MENU EMPRESA
		submenu = new Submenu();
		submenu.setIcon("ui-icon ui-icon-circle-triangle-e");
		submenu.setLabel("Gerar Relat�rios");
		
		
			item = new MenuItem();
			item.setIcon("ui-icon ui-icon-folder-open");
			item.setValue("Relat�rio Individual do Total de Horas");
			item.setUrl("/admin/relatorios/consultarTotalHoras.xhtml");
			submenu.getChildren().add(item);
			
			item = new MenuItem();
			item.setIcon("ui-icon ui-icon-folder-open");
			item.setValue("Total de Horas Realizadas por aluno");
			item.setUrl("/admin/relatorios/findRelHorasAluno.xhtml");
			submenu.getChildren().add(item);
			
			item = new MenuItem();
			item.setIcon("ui-icon ui-icon-folder-open");
			item.setValue("Total de Horas para Termino do Est�gio por aluno");
			item.setUrl("/admin/relatorios/findRelFaltaHorasAluno.xhtml");
			submenu.getChildren().add(item);
			
		model.addSubmenu(submenu);
		
		
		// MENU Supervisor de Estagio
		
		if (getTipoAcesso() == 1) {
			
			model.addSubmenu(submenu);			

			submenu = new Submenu();
			submenu.setIcon("ui-icon ui-icon-circle-triangle-e");
			submenu.setLabel("Supervisor de Est�gio");
			
					// Usu�rio
					submenuChildren = new Submenu();
					submenuChildren.setIcon("ui-icon ui-icon-circle-triangle-e");
					submenuChildren.setLabel("Usu�rio");
						
						item = new MenuItem();
						item.setIcon("ui-icon ui-icon-document");
						item.setValue("Novo Usu�rio");
						item.setUrl("/admin/user/incluir.xhtml");
						submenuChildren.getChildren().add(item);
		
						item = new MenuItem();
						item.setIcon("ui-icon ui-icon-folder-open");
						item.setValue("Listar Usu�rios");
						item.setUrl("/admin/user/user.xhtml");
						submenuChildren.getChildren().add(item);
						
					submenu.getChildren().add(submenuChildren);
					
					// Relat�rio de Horas Mensal
					submenuChildren = new Submenu();
					submenuChildren.setIcon("ui-icon ui-icon-circle-triangle-e");
					submenuChildren.setLabel("Relat�rio Mensal de Atividades");
						
						item = new MenuItem();
						item.setIcon("ui-icon ui-icon-document");
						item.setValue("Novo Relat�rio Mensal de Atividades");
						item.setUrl("/admin/relatorioMensal/incluir.xhtml");
						submenuChildren.getChildren().add(item);
		
						item = new MenuItem();
						item.setIcon("ui-icon ui-icon-folder-open");
						item.setValue("Listar Relat�rios Mensais de Atividades");
						item.setUrl("/admin/relatorioMensal/relatorioMensal.xhtml");
						submenuChildren.getChildren().add(item);
						
					submenu.getChildren().add(submenuChildren);
				
			model.addSubmenu(submenu);
		}

	}

	public MenuModel getModel() {
		return model;
	}

	private int getTipoAcesso() {
		int tipo = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		try {

			tipo = (Integer) session.getAttribute("tipo");
		} catch (Exception e) {
			tipo = 0;
		}

		return tipo;
	}

}