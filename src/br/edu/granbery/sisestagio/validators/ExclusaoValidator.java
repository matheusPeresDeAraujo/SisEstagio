package br.edu.granbery.sisestagio.validators;

import java.util.List;

import br.edu.granbery.sisestagio.dao.AlunoDaoImpl;
import br.edu.granbery.sisestagio.dao.DistratoDaoImpl;
import br.edu.granbery.sisestagio.dao.ProjetoEstagioDaoImpl;
import br.edu.granbery.sisestagio.dao.ProjetoFinalDaoImpl;
import br.edu.granbery.sisestagio.dao.RelatorioAcompanhamentoDaoImpl;
import br.edu.granbery.sisestagio.dao.RelatorioMensalDaoImpl;
import br.edu.granbery.sisestagio.dao.TermoAditivoDaoImpl;
import br.edu.granbery.sisestagio.dao.TermoCompromissoDaoImpl;
import br.edu.granbery.sisestagio.model.Aluno;
import br.edu.granbery.sisestagio.model.Curriculo;
import br.edu.granbery.sisestagio.model.Distrato;
import br.edu.granbery.sisestagio.model.Empresa;
import br.edu.granbery.sisestagio.model.ProjetoEstagio;
import br.edu.granbery.sisestagio.model.ProjetoFinal;
import br.edu.granbery.sisestagio.model.RelatorioAcompanhamento;
import br.edu.granbery.sisestagio.model.RelatorioMensal;
import br.edu.granbery.sisestagio.model.TermoAditivo;
import br.edu.granbery.sisestagio.model.TermoCompromisso;

public class ExclusaoValidator {

	private static ExclusaoValidator instance = new ExclusaoValidator();

	public static ExclusaoValidator getInstance() {
		return instance;
	}

	public void exclusaoValidatorEmpresa(Empresa empresa) throws Exception {

		TermoCompromissoDaoImpl daoImpl = new TermoCompromissoDaoImpl();

		daoImpl.getSession();

		List<TermoCompromisso> termoCompromissos = daoImpl.findAll(
				"empresa.idEmpresa", empresa.getIdEmpresa());

		daoImpl.sessionClose();

		if (termoCompromissos.size() > 0) {
			throw new Exception(
					"Erro ao excluir a Empresa "
							+ empresa.getNome()
							+ ": Esta empresa tem um ou mais Termos de Compromisso Associada a ela.");
		}

	}

	public void exclusaoValidatorCurriculo(Curriculo curriculo)
			throws Exception {

		AlunoDaoImpl alunoDaoImpl = new AlunoDaoImpl();

		alunoDaoImpl.getSession();

		List<Aluno> alunos = alunoDaoImpl.findAll("curriculo.idCurriculo",
				curriculo.getIdCurriculo());

		alunoDaoImpl.sessionClose();

		if (alunos.size() > 0) {
			throw new Exception("Erro ao excluir o curriculo de número "
					+ curriculo.getNumero()
					+ ": Este Currículo tem um ou mais Alunos Associada a ele.");
		}

	}

	public void exclusaoValidatorTermoCompromisso(
			TermoCompromisso termoCompromisso) throws Exception {

		DistratoDaoImpl distratoDaoImpl = new DistratoDaoImpl();

		distratoDaoImpl.getSession();

		Distrato distrato = distratoDaoImpl.findField(
				"termoDeCompromisso.idTermoDeCompromisso",
				termoCompromisso.getIdTermoDeCompromisso());

		distratoDaoImpl.sessionClose();

		if (distrato != null) {
			throw new Exception(
					"Erro ao excluir Termo de Compromisso do Aluno "
							+ termoCompromisso.getAluno().getNome()
							+ ": Este Termo de Compromisso tem um Distrato Associada a ele.");
		}

		TermoAditivoDaoImpl termoAditivoDaoImpl = new TermoAditivoDaoImpl();

		termoAditivoDaoImpl.getSession();

		List<TermoAditivo> termoAditivos = termoAditivoDaoImpl.findAll(
				"termoDeCompromisso.idTermoDeCompromisso",
				termoCompromisso.getIdTermoDeCompromisso());

		termoAditivoDaoImpl.sessionClose();

		if (termoAditivos.size() > 0) {
			throw new Exception(
					"Erro ao excluir Termo de Compromisso do Aluno "
							+ termoCompromisso.getAluno().getNome()
							+ ": Este Termo de Compromisso tem um ou mais Termos Aditivo Associada a ele.");
		}

	}

	public void exclusaoValidatorAluno(Aluno aluno) throws Exception {

		ProjetoEstagioDaoImpl projetoEstagioDaoImpl = new ProjetoEstagioDaoImpl();
		
		projetoEstagioDaoImpl.getSession();

		ProjetoEstagio projetoEstagio = projetoEstagioDaoImpl.findField(
				"aluno.idAluno", aluno.getIdAluno());
		
		projetoEstagioDaoImpl.sessionClose();

		if (projetoEstagio != null) {
			throw new Exception("Erro ao excluir o Aluno " + aluno.getNome()
					+ ": Este Aluno tem um Projeto de Estágio Associada a ele.");
		}
		
		

		ProjetoFinalDaoImpl projetoFinalDaoImpl = new ProjetoFinalDaoImpl();
		
		projetoFinalDaoImpl.getSession();

		ProjetoFinal projetoFinal = projetoFinalDaoImpl.findField(
				"aluno.idAluno", aluno.getIdAluno());

		projetoFinalDaoImpl.sessionClose();
		
		if (projetoFinal != null) {
			throw new Exception("Erro ao excluir o Aluno " + aluno.getNome()
					+ ": Este Aluno tem um Projeto Final Associada a ele.");
		}
		
		
		

		RelatorioAcompanhamentoDaoImpl relAcompanhamentoDao = new RelatorioAcompanhamentoDaoImpl();
		
		relAcompanhamentoDao.getSession();

		RelatorioAcompanhamento relAcompanhamento = relAcompanhamentoDao
				.findField("aluno.idAluno", aluno.getIdAluno());
		
		relAcompanhamentoDao.sessionClose();

		if (relAcompanhamento != null) {
			throw new Exception(
					"Erro ao excluir o Aluno "
							+ aluno.getNome()
							+ ": Este Aluno tem um Relatório de Acompanhamento Associada a ele.");
		}
		
		
		

		TermoCompromissoDaoImpl termoCompromissoDao = new TermoCompromissoDaoImpl();
		
		termoCompromissoDao.getSession();

		List<TermoCompromisso> termoCompromissos = termoCompromissoDao.findAll(
				"aluno.idAluno", aluno.getIdAluno());
		
		termoCompromissoDao.sessionClose();

		if (termoCompromissos.size() > 0) {
			throw new Exception(
					"Erro ao excluir o Aluno "
							+ aluno.getNome()
							+ ": Este Aluno tem um ou mais Termos de Compromisso Associada a ele.");
		}
		
		

		RelatorioMensalDaoImpl relMensalDao = new RelatorioMensalDaoImpl();
		
		relMensalDao.getSession();

		List<RelatorioMensal> relatorioMensals = relMensalDao.findAll(
				"aluno.idAluno", aluno.getIdAluno());
		
		relMensalDao.sessionClose();

		if (relatorioMensals.size() > 0) {
			throw new Exception(
					"Erro ao excluir o Aluno "
							+ aluno.getNome()
							+ ": Este Aluno tem um ou mais Relatórios Mensal Associada a ele.");
		}

	}

}
