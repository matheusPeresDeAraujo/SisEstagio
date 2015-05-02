package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.granbery.sisestagio.dao.EmpresaDaoImpl;
import br.edu.granbery.sisestagio.model.Empresa;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.validators.CNPJDuplicadoValidator;
import br.edu.granbery.sisestagio.validators.ExclusaoValidator;

@ManagedBean(name = "empresaBean")
@RequestScoped
public class EmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Empresa empresa;
	private EmpresaDaoImpl empresaDaoImpl;
	private List<Empresa> filteredEmpresas;

	public EmpresaBean() {
		this.empresa = new Empresa();
		this.empresaDaoImpl = new EmpresaDaoImpl();
	}

	public void salvaEmpresa() {
		try {
			empresaDaoImpl.getSession();		
			
			empresaDaoImpl.save(empresa);
			MessagesUtil.info("Empresa " + empresa.getNome()
					+ " salvo com sucesso.");
			this.empresa = new Empresa();
		} catch (Exception e) {
			MessagesUtil.error("Erro ao salvar Empresa " + empresa.getNome());
		} finally {
			empresaDaoImpl.sessionClose();
		}
	}

	public void updateEmpresa() {
		try {
			
			empresaDaoImpl.getSession();

			/**
			 * Validador responsável por garantir que não já N° de cnpj
			 * duplicado na tabela empresa do banco de dados
			 */
			CNPJDuplicadoValidator.getInstance().cnpjValidatorUpdate(
					empresa.getCnpj(), empresa.getIdEmpresa());

			empresaDaoImpl.update(empresa);
			MessagesUtil.info("Dados do Empresa " + empresa.getNome()
					+ " alterado com sucesso.");
			this.empresa = new Empresa();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil.error("Erro ao alterar os dados da Empresa "
						+ empresa.getNome());
			}
		} finally {
			empresaDaoImpl.sessionClose();
		}

	}

	public void excluirEmpresa() {
		try {
			
			// Validar exclusão

			ExclusaoValidator.getInstance().exclusaoValidatorEmpresa(empresa);
			
			empresaDaoImpl.getSession();

			empresaDaoImpl.remove(empresa);
			MessagesUtil.info("Empresa " + empresa.getNome()
					+ " excluindo com sucesso.");
			this.empresa = new Empresa();
			this.filteredEmpresas = empresaDaoImpl.findAll("nome");
			empresaDaoImpl.sessionClose();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil.error("Erro ao excluir a Empresa "
						+ empresa.getNome());
				empresaDaoImpl.sessionClose();
			}
		}
	}

	public List<Empresa> getEmpresas() {
		empresaDaoImpl.getSession();
		
		List<Empresa> empresas = empresaDaoImpl.findAll("nome");
		
		empresaDaoImpl.sessionClose();
		
		return empresas;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getFilteredEmpresas() {
		return filteredEmpresas;
	}

	public void setFilteredEmpresas(List<Empresa> filteredEmpresas) {
		this.filteredEmpresas = filteredEmpresas;
	}

}
