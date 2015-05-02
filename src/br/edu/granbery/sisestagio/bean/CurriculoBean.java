package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.granbery.sisestagio.dao.CurriculoDaoImpl;
import br.edu.granbery.sisestagio.model.Curriculo;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.validators.ExclusaoValidator;
import br.edu.granbery.sisestagio.validators.NumeroCurriculoDisponibilidadeValidator;

@ManagedBean(name = "curriculoBean")
@RequestScoped
public class CurriculoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Curriculo curriculo;
	private CurriculoDaoImpl curriculoDaoImpl;
	private List<Curriculo> filteredCurriculos;

	public CurriculoBean() {
		this.curriculo = new Curriculo();
		this.curriculoDaoImpl = new CurriculoDaoImpl();
	}

	public void salvaCurriculo() {
		try {
			curriculoDaoImpl.getSession();
			
			curriculoDaoImpl.save(curriculo);
			MessagesUtil.info("Curriculo de número " + curriculo.getNumero()
					+ " salvo com sucesso.");
			this.curriculo = new Curriculo();

		} catch (Exception e) {
			MessagesUtil.error("Erro ao salvar curriculo de número "
					+ curriculo.getNumero());
		}finally{
			curriculoDaoImpl.sessionClose();
		}

	}

	public void updateCurriculo() {
		try {

			curriculoDaoImpl.getSession();
			
			/**
			 * Validador responsável por garantir que não já N° de Numero
			 * duplicado na tabela curriculo do banco de dados
			 */
			NumeroCurriculoDisponibilidadeValidator.getInstance()
					.CurriculoValidatorUpdate(curriculo.getNumero(),
							curriculo.getIdCurriculo());

			curriculoDaoImpl.update(curriculo);
			MessagesUtil.info("Dados do curriculo de número "
					+ curriculo.getNumero() + " alterado com sucesso.");
			this.curriculo = new Curriculo();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil
						.error("Erro ao alterar os dados do curriculo de número "
								+ curriculo.getNumero());
			}
		}finally{
			curriculoDaoImpl.sessionClose();
		}
	}

	public void excluirCurriculo() {
		try {			

			// Validar exclusão
			ExclusaoValidator.getInstance().exclusaoValidatorCurriculo(curriculo);
			
			curriculoDaoImpl.getSession();

			curriculoDaoImpl.remove(curriculo);
			MessagesUtil.info("O curriculo de número " + curriculo.getNumero()
					+ " excluindo com sucesso.");
			this.curriculo = new Curriculo();
			this.filteredCurriculos = curriculoDaoImpl.findAll("numero");
			curriculoDaoImpl.sessionClose();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil.error("Erro ao excluir o curriculo de número "
						+ curriculo.getNumero());
				curriculoDaoImpl.sessionClose();
			}			
		}
	}

	public List<Curriculo> getCurriculos() {
		curriculoDaoImpl.getSession();
		
		List<Curriculo> curriculos = curriculoDaoImpl.findAll("numero");
		
		curriculoDaoImpl.sessionClose();
		
		return curriculos;
	}

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}

	public List<Curriculo> getFilteredCurriculos() {
		return filteredCurriculos;
	}

	public void setFilteredCurriculos(List<Curriculo> filteredCurriculos) {
		this.filteredCurriculos = filteredCurriculos;
	}

}
