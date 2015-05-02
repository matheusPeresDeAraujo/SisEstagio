package br.edu.granbery.sisestagio.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.granbery.sisestagio.dao.UsuarioDaoImpl;
import br.edu.granbery.sisestagio.encryption.Encryption;
import br.edu.granbery.sisestagio.model.Usuario;
import br.edu.granbery.sisestagio.util.MessagesUtil;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private UsuarioDaoImpl usuarioDaoImpl;
	private List<Usuario> filteredUsuarios;

	public UsuarioBean() {
		this.usuario = new Usuario();
		this.usuarioDaoImpl = new UsuarioDaoImpl();
	}

	public void salvaUsuario() {
		try {
			usuarioDaoImpl.getSession();

			usuarioDaoImpl.save(usuario);

			MessagesUtil.info("Usuário " + usuario.getNome()
					+ " salvo com sucesso.");

			this.usuario = new Usuario();
		} catch (Exception e) {
			MessagesUtil.error("Erro ao salvar usuário " + usuario.getNome()
					+ ": ");
		} finally {
			usuarioDaoImpl.sessionClose();
		}
	}

	public void updateUsuario() {
		try {
			usuarioDaoImpl.getSession();

			usuarioDaoImpl.update(usuario);
			MessagesUtil.info("Dados do Usuário " + usuario.getNome()
					+ " alterado com sucesso.");
			this.usuario = new Usuario();
		} catch (Exception e) {
			MessagesUtil.error("Erro ao alterar os dados do usuário "
					+ usuario.getNome() + ": ");
		} finally {
			usuarioDaoImpl.sessionClose();
		}

	}

	public void definirSenhaUsuario() {
		try {
			usuarioDaoImpl.getSession();

			usuario.setSenha(Encryption.getInstance().encryptionMd5(
					usuario.getSenha()));

			usuarioDaoImpl.update(usuario);
			MessagesUtil.info("Senha do Usuário " + usuario.getNome()
					+ " definida com sucesso.");
			this.usuario = new Usuario();
		} catch (Exception e) {
			MessagesUtil.error("Erro ao difinir do usuário "
					+ usuario.getNome() + ": ");
		} finally {
			usuarioDaoImpl.sessionClose();
		}

	}

	public void excluir() {
		try {
			usuarioDaoImpl.getSession();

			usuarioDaoImpl.remove(usuario);
			MessagesUtil.info("Usuário " + usuario.getNome()
					+ " excluindo com sucesso.");
			this.usuario = new Usuario();
			this.filteredUsuarios = usuarioDaoImpl.findAll("nome");
		} catch (Exception e) {
			MessagesUtil.error("Erro ao excluir usuário " + usuario.getNome()
					+ ": ");
		} finally {
			usuarioDaoImpl.sessionClose();
		}
	}

	public List<Usuario> getUsuarios() {
		usuarioDaoImpl.getSession();

		List<Usuario> usuarios = usuarioDaoImpl.findAll("nome");

		usuarioDaoImpl.sessionClose();

		return usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}

}
