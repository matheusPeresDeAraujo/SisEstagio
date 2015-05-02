package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.Aluno;

public interface AlunoDao extends GenericDao<Aluno, Long> {
	public List<Aluno> recuperaPorNome(String nome);
	public Aluno findAlunoByCampoFilter(String nomeCampoFilter, String valorCampoFilter);
}
