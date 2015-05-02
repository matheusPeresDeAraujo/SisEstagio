package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.granbery.sisestagio.dao.AlunoDaoImpl;
import br.edu.granbery.sisestagio.dao.RelatorioMensalDaoImpl;
import br.edu.granbery.sisestagio.model.Aluno;
import br.edu.granbery.sisestagio.model.RelatorioMensal;
import br.edu.granbery.sisestagio.relatorio.RelRelatorioMensal;

@ManagedBean(name = "relRelatorioMensalBean")
@RequestScoped
public class RelRelatorioMensalBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private AlunoDaoImpl alunoDaoImpl;
	private List<RelRelatorioMensal> filteredRelRelatorioMensal;

	public RelRelatorioMensalBean() {
		alunoDaoImpl = new AlunoDaoImpl();
	}

	public List<RelRelatorioMensal> getRelRelatorioMensais() {
		try {

			List<RelRelatorioMensal> relRelatorioMensais = new ArrayList<RelRelatorioMensal>();
			Aluno aluno = new Aluno();
			double totalHorasRegistradas = 0;
			double totalHorasPenalizadas = 0;
			double totalHorasAproveitadas = 0;

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context
					.getExternalContext().getRequest();
			HttpSession session = request.getSession();

			aluno = (Aluno) session.getAttribute("aluno");

			if (aluno == null) {
				String vUrl = "";
				vUrl = request.getContextPath() + "/index.xhtml";
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(vUrl);
			}

			RelatorioMensalDaoImpl relMensalDaoImpl = new RelatorioMensalDaoImpl();

			relMensalDaoImpl.getSession();

			List<RelatorioMensal> relMensals = relMensalDaoImpl.findAll("aluno.idAluno", aluno.getIdAluno());

			relMensalDaoImpl.sessionClose();

			for (int i = 0; i < relMensals.size(); i++) {
				totalHorasRegistradas += relMensals.get(i)
						.getHorasRegistradas();

				totalHorasPenalizadas += relMensals.get(i)
						.getHorasPenalizadas();

				totalHorasAproveitadas += relMensals.get(i)
						.getHorasAproveitadas();

			}

			RelRelatorioMensal relRelatorioMensal = new RelRelatorioMensal();
			relRelatorioMensal.setNome(aluno.getNome());
			relRelatorioMensal.setTotalHorasRegistradas(totalHorasRegistradas);
			relRelatorioMensal.setTotalHorasPenalizadas(totalHorasPenalizadas);
			relRelatorioMensal
					.setTotalHorasAproveitadas(totalHorasAproveitadas);

			relRelatorioMensais.add(relRelatorioMensal);

			return relRelatorioMensais;
		} catch (Exception e) {
			return null;
		}

	}

	public List<RelRelatorioMensal> getFindRelHorasAluno() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		List<RelRelatorioMensal> relRelatorioMensais = new ArrayList<RelRelatorioMensal>();

		alunos = alunoDaoImpl.findAll("nome");

		for (int i = 0; i < alunos.size(); i++) {
			RelRelatorioMensal relRelatorioMensal = new RelRelatorioMensal();
			double totalHorasRegistradas = 0;
			double totalHorasPenalizadas = 0;
			double totalHorasAproveitadas = 0;

			for (int j = 0; j < alunos.get(i).getRelatorioMensais().size(); j++) {

				totalHorasRegistradas += alunos.get(i).getRelatorioMensais()
						.get(j).getHorasRegistradas();

				totalHorasPenalizadas += alunos.get(i).getRelatorioMensais()
						.get(j).getHorasPenalizadas();

				totalHorasAproveitadas += alunos.get(i).getRelatorioMensais()
						.get(j).getHorasAproveitadas();
			}

			relRelatorioMensal.setNome(alunos.get(i).getNome());
			relRelatorioMensal.setTotalHorasRegistradas(totalHorasRegistradas);
			relRelatorioMensal.setTotalHorasPenalizadas(totalHorasPenalizadas);
			relRelatorioMensal
					.setTotalHorasAproveitadas(totalHorasAproveitadas);

			relRelatorioMensais.add(relRelatorioMensal);
		}

		return relRelatorioMensais;
	}

	public List<RelRelatorioMensal> getFindRelFaltaHorasAluno() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		List<RelRelatorioMensal> relRelatorioMensais = new ArrayList<RelRelatorioMensal>();

		alunos = alunoDaoImpl.findAll("nome");

		for (int i = 0; i < alunos.size(); i++) {
			RelRelatorioMensal relRelatorioMensal = new RelRelatorioMensal();
			double totalHorasFalta = 0;
			double totalHorasAproveitadas = 0;

			for (int j = 0; j < alunos.get(i).getRelatorioMensais().size(); j++) {
				totalHorasAproveitadas += alunos.get(i).getRelatorioMensais()
						.get(j).getHorasAproveitadas();
			}

			totalHorasFalta = alunos.get(i).getCurriculo().getHorasDeEstagio()
					- totalHorasAproveitadas;

			if (totalHorasFalta < 0) {

				totalHorasFalta = 0;
			}

			relRelatorioMensal.setNome(alunos.get(i).getNome());
			relRelatorioMensal.setTotalHorasAproveitadas(totalHorasFalta);

			relRelatorioMensais.add(relRelatorioMensal);
		}

		return relRelatorioMensais;
	}

	public List<RelRelatorioMensal> getFilteredRelRelatorioMensal() {
		return filteredRelRelatorioMensal;
	}

	public void setFilteredRelRelatorioMensal(
			List<RelRelatorioMensal> filteredRelRelatorioMensal) {
		this.filteredRelRelatorioMensal = filteredRelRelatorioMensal;
	}

}
