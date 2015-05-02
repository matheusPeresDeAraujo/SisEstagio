package br.edu.granbery.sisestagio.dao;

import java.util.List;

import br.edu.granbery.sisestagio.model.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Long> {
	public List<Usuario> recuperaPorNome(String nome);
	public boolean emailDisponibilidade(String email);
}
