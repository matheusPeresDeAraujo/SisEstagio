package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.granbery.sisestagio.dao.RelatorioMensalDaoImpl;
import br.edu.granbery.sisestagio.model.RelatorioMensal;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.validators.DataValidator;
import br.edu.granbery.sisestagio.validators.HoraValidator;
import br.edu.granbery.sisestagio.validators.ProtocoloValidator;

@ManagedBean(name = "relatorioMensalBean")
@RequestScoped
public class RelatorioMensalBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private RelatorioMensal relatorioMensal;
	private RelatorioMensalDaoImpl relatorioMensalDaoImpl;
	private List<RelatorioMensal> filteredRelatorioMensal;

	public RelatorioMensalBean() {
		this.relatorioMensal = new RelatorioMensal();
		this.relatorioMensalDaoImpl = new RelatorioMensalDaoImpl();
	}

	public void salvaRelatorioMensal() {
		boolean vReturn = false;
		double vHorasPenalizadas = 0;

		try {

			relatorioMensalDaoImpl.getSession();

			DataValidator dataValidator = new DataValidator();

			vReturn = dataValidator.validateEndDate(
					relatorioMensal.getDataInicioAtividade(),
					relatorioMensal.getDataTerminoAtividade());

			HoraValidator.getInstance().validateEndHora(
					relatorioMensal.getHorasRegistradas(),
					relatorioMensal.getHorasAproveitadas());

			vHorasPenalizadas = relatorioMensal.getHorasRegistradas()
					- relatorioMensal.getHorasAproveitadas();

			relatorioMensal.setHorasPenalizadas(vHorasPenalizadas);

			relatorioMensalDaoImpl.save(relatorioMensal);
			MessagesUtil.info("Relatório Mensal de Atividades para Aluno "
					+ relatorioMensal.getAluno().getNome()
					+ " salvo com sucesso.");
			this.relatorioMensal = new RelatorioMensal();
		} catch (Exception e) {

			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {

				System.out.println("Erro: " + e.getMessage());

				if (vReturn) {
					MessagesUtil
							.error("Erro ao salvar Relatório Mensal de Atividades para Aluno "
									+ relatorioMensal.getAluno().getNome()
									+ ": Já tem Relatório Mensal de Atividades cadastro no banco de dados!");
				} else {
					MessagesUtil
							.error("Data de Início precisa ser menor ou igual que Data de Termino.");
				}

			}

		} finally {
			relatorioMensalDaoImpl.sessionClose();
		}

	}

	public void updateRelatorioMensal() {
		boolean vReturn = false;
		double vHorasPenalizadas = 0;

		try {
			relatorioMensalDaoImpl.getSession();

			if (relatorioMensalDaoImpl.findFields("idRelatorioMensal",
					relatorioMensal.getIdRelatorioMensal(), "protocolo",
					relatorioMensal.getProtocolo()) == null) {
				ProtocoloValidator.getInstance().ProtocoloValidatorUpdate(
						relatorioMensal.getProtocolo());
			}

			DataValidator dataValidator = new DataValidator();

			vReturn = dataValidator.validateEndDate(
					relatorioMensal.getDataInicioAtividade(),
					relatorioMensal.getDataTerminoAtividade());

			HoraValidator.getInstance().validateEndHora(
					relatorioMensal.getHorasRegistradas(),
					relatorioMensal.getHorasAproveitadas());

			vHorasPenalizadas = relatorioMensal.getHorasRegistradas()
					- relatorioMensal.getHorasAproveitadas();

			relatorioMensal.setHorasPenalizadas(vHorasPenalizadas);

			relatorioMensalDaoImpl.update(relatorioMensal);
			MessagesUtil.info("Dados do Relatório Mensal de Atividades para Aluno "
					+ relatorioMensal.getAluno().getNome()
					+ " alterado com sucesso.");
			this.relatorioMensal = new RelatorioMensal();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				if (vReturn) {
					MessagesUtil
							.error("Erro ao alterar os dados do Relatório Mensal de Atividades para Aluno "
									+ relatorioMensal.getAluno().getNome()
									+ ": Já tem Relatório Mensal de Atividades cadastro no banco de dados!");
				} else {
					MessagesUtil
							.error("Data de Início precisa ser menor ou igual que Data de Termino.");
				}
			}

		} finally {
			relatorioMensalDaoImpl.sessionClose();
		}

	}

	public void excluir() {
		try {

			relatorioMensalDaoImpl.getSession();

			relatorioMensalDaoImpl.remove(relatorioMensal);
			MessagesUtil.info("Relatório Mensal de Atividades do Aluno "
					+ relatorioMensal.getAluno().getNome()
					+ " excluindo com sucesso.");
			this.relatorioMensal = new RelatorioMensal();
			this.filteredRelatorioMensal = relatorioMensalDaoImpl
					.findAll("protocolo");
		} catch (Exception e) {
			MessagesUtil
					.error("Erro ao excluir Relatório Mensal de Atividades do Aluno "
							+ relatorioMensal.getAluno().getNome());
		} finally {
			relatorioMensalDaoImpl.sessionClose();
		}
	}

	public List<RelatorioMensal> getRelatorioMensais() {
		relatorioMensalDaoImpl.getSession();

		List<RelatorioMensal> relatorioMensals = relatorioMensalDaoImpl
				.findAll("protocolo");

		relatorioMensalDaoImpl.sessionClose();

		return relatorioMensals;
	}

	public RelatorioMensal getRelatorioMensal() {
		return relatorioMensal;
	}

	public void setRelatorioMensal(RelatorioMensal relatorioMensal) {
		this.relatorioMensal = relatorioMensal;
	}

	public List<RelatorioMensal> getFilteredRelatorioMensal() {
		return filteredRelatorioMensal;
	}

	public void setFilteredRelatorioMensal(
			List<RelatorioMensal> filteredRelatorioMensal) {
		this.filteredRelatorioMensal = filteredRelatorioMensal;
	}

}
