package br.edu.granbery.sisestagio.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.granbery.sisestagio.dao.UsuarioDaoImpl;
import br.edu.granbery.sisestagio.encryption.Encryption;
import br.edu.granbery.sisestagio.model.Usuario;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.validators.AuthenticatorValidator;

@ManagedBean(name = "authenticatorBean")
@RequestScoped
public class AuthenticatorBean {
	private Usuario usuario;
	private UsuarioDaoImpl usuarioDaoImpl;

	public AuthenticatorBean() {
		usuario = new Usuario();
		usuarioDaoImpl = new UsuarioDaoImpl();
	}

	public void login() {

		try {
			usuarioDaoImpl.getSession();
			String vUrl = "";
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context
					.getExternalContext().getRequest();

			usuario = AuthenticatorValidator.getInstance().validateLogin(
					usuario.getEmail(),
					Encryption.getInstance().encryptionMd5(usuario.getSenha()));

			HttpSession session = request.getSession();
			session.setAttribute("idUsuario", usuario.getIdUsuario());
			session.setAttribute("userNome", usuario.getNome());
			session.setAttribute("tipo", usuario.getTipoAcesso());
			session.setAttribute("objUsuario", usuario);

			vUrl = request.getContextPath() + "/admin/adminprincipal.xhtml";
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(vUrl);
		} catch (Exception e) {
			MessagesUtil.error("Erro: O Login e/ou a Senha estão incorretos");
		}finally{
			usuarioDaoImpl.sessionClose();
		}

	}

	public void logout() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context
					.getExternalContext().getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("idUsuario", null);
			session.setAttribute("userNome", null);
			session.setAttribute("objUsuario", null);
			session.setAttribute("tipo", 0);

			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(request.getContextPath() + "/admin/admin.xhtml");

		} catch (Exception e) {
			MessagesUtil.error("Erro ao fazer logout");
		}
	}

	public void upPwd() {
		try {

			usuarioDaoImpl.getSession();
			
			Usuario usuarioTemp;

			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context
					.getExternalContext().getRequest();
			HttpSession session = request.getSession();

			usuarioTemp = (Usuario) session.getAttribute("objUsuario");

			String vPwd = "";

			vPwd = Encryption.getInstance().encryptionMd5(usuario.getSenha());

			usuario = usuarioTemp;

			usuario.setSenha(vPwd);

			usuarioDaoImpl.merge(usuario);

			MessagesUtil.info("Sua senha alterada com sucesso!");
		} catch (Exception e) {
			MessagesUtil.error("Erro ao Alterar sua senha!");
		}finally{
			usuarioDaoImpl.sessionClose();
		}
	}

	public String getNome() {
		String vUserNome = "";

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		try {
			vUserNome = (String) session.getAttribute("userNome");
		} catch (Exception e) {
			vUserNome = "";
		}
		return vUserNome;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
