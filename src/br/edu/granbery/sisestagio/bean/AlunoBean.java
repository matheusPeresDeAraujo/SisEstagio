package br.edu.granbery.sisestagio.bean;

import java.io.IOException;
import java.io.Serializable;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.granbery.sisestagio.dao.AlunoDaoImpl;
import br.edu.granbery.sisestagio.encryption.Encryption;
import br.edu.granbery.sisestagio.model.Aluno;
import br.edu.granbery.sisestagio.util.MessagesUtil;
import br.edu.granbery.sisestagio.util.RandomPass;
import br.edu.granbery.sisestagio.util.SendMailTLS;
import br.edu.granbery.sisestagio.validators.AuthenticatorValidator;
import br.edu.granbery.sisestagio.validators.EmailDuplicadoValidator;
import br.edu.granbery.sisestagio.validators.ExclusaoValidator;
import br.edu.granbery.sisestagio.validators.MatriculaValidator;

@ManagedBean(name = "alunoBean")
@RequestScoped
public class AlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	private AlunoDaoImpl alunoDaoImpl;
	private List<Aluno> filteredAluno;

	public AlunoBean() {
		this.aluno = new Aluno();
		this.alunoDaoImpl = new AlunoDaoImpl();
	}

	public void salvaAluno() {
		try {
			
			alunoDaoImpl.getSession();
			
			alunoDaoImpl.save(aluno);
			MessagesUtil.info("Aluno " + aluno.getNome()
					+ " salvo com sucesso.");
			this.aluno = new Aluno();
		} catch (Exception e) {
			MessagesUtil.error("Erro ao salvar Aluno " + aluno.getNome());
		}finally{
			alunoDaoImpl.sessionClose();
		}


	}

	public void updateAluno() {
		try {
			
			alunoDaoImpl.getSession();

			/**
			 * Validador responsável por garantir que não já N° de Matricula
			 * duplicado na tabela aluno do banco de dados
			 */
			MatriculaValidator.getInstance().MatriculaValidatorUpdate(
					aluno.getMatricula(), aluno.getIdAluno());

			/**
			 * Validador responsável por garantir que não já e-mail duplicado na
			 * tabela aluno do banco de dados
			 */
			EmailDuplicadoValidator.getInstance().validateEmailToUpdate(
					aluno.getIdAluno(), aluno.getEmail());

			alunoDaoImpl.update(aluno);
			MessagesUtil.info("Dados do Aluno " + aluno.getNome()
					+ " alterado com sucesso.");
			this.aluno = new Aluno();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil.error("Erro ao alterar os dados do Aluno "
						+ aluno.getNome());
			}
		}finally{
			alunoDaoImpl.sessionClose();
		}

	}

	public void excluirAluno() {
		try {
			
			ExclusaoValidator.getInstance().exclusaoValidatorAluno(aluno);
			
			alunoDaoImpl.getSession();

			alunoDaoImpl.remove(aluno);
			MessagesUtil.info("O Aluno " + aluno.getNome()
					+ " excluindo com sucesso.");
			this.aluno = new Aluno();
			this.filteredAluno = alunoDaoImpl.findAll("nome");
			alunoDaoImpl.sessionClose();
		} catch (Exception e) {
			if (e.getMessage() != null) {
				MessagesUtil.error(e.getMessage());
			} else {
				MessagesUtil
						.error("Erro ao excluir o Aluno " + aluno.getNome());
				alunoDaoImpl.sessionClose();
			}

		}
	}

	public void definirSenhaUsuario() {
		try {
			
			alunoDaoImpl.getSession();
			
			aluno.setSenha(Encryption.getInstance().encryptionMd5(
					aluno.getSenha()));

			alunoDaoImpl.update(aluno);
			
			MessagesUtil.info("Senha do Aluno " + aluno.getNome()
					+ " definida com sucesso.");
			
			this.aluno = new Aluno();
		} catch (Exception e) {
			MessagesUtil.error("Erro ao difinir do aluno " + aluno.getNome()
					+ ": ");
		}finally{
			alunoDaoImpl.sessionClose();
		}

	}

	public void consultaByMatriculaSession() {
		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

		HttpSession session = request.getSession();
		
		alunoDaoImpl.getSession();
		
		aluno = alunoDaoImpl.findAlunoByCampoFilter("matricula", aluno.getMatricula());

		session.setAttribute("aluno", aluno);
		
		if(aluno.getRelatorioMensais() == null){
			System.out.println("Erro Null");
		}

		String vUrl = "";
		vUrl = request.getContextPath() + "/admin/relatorios/resultado.xhtml";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(vUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			alunoDaoImpl.sessionClose();
		}
	}

	public void consultaByMatriculaEndSenhaSession() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();

			HttpServletRequest request = (HttpServletRequest) context
					.getExternalContext().getRequest();

			HttpSession session = request.getSession();

			alunoDaoImpl.getSession();
			
			aluno.setSenha(Encryption.getInstance().encryptionMd5(
					aluno.getSenha()));

			aluno = AuthenticatorValidator.getInstance().validateAlunoConsulta(
					aluno.getMatricula(), aluno.getSenha());

			session.setAttribute("aluno", aluno);

			String vUrl = "";
			vUrl = request.getContextPath() + "/resultado.xhtml";

			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(vUrl);
		} catch (Exception e) {
			MessagesUtil.error("Erro: " + e.getMessage());
		}finally{
			alunoDaoImpl.sessionClose();
		}
	}

	public void recuperarSenhaRedirect() {
		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String vUrl = "";
		vUrl = request.getContextPath() + "/esqueciMinhaSenha.xhtml";

		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(vUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void esqueciMinhaSenha() {
		try {
			
			alunoDaoImpl.getSession();
			
			aluno = AuthenticatorValidator.getInstance().validateEsqueciMinhaSenha(aluno.getMatricula(), aluno.getEmail());

			String pwd = "";

			pwd = RandomPass.getInstance().getRandomPass(15);

			aluno.setSenha(Encryption.getInstance().encryptionMd5(pwd));

			alunoDaoImpl.merge(aluno);
			
			System.out.println("Pwd: " + pwd);

			String vMsg = "";

			vMsg = "Olá, "+ aluno.getNome() +"! " +
			"\n\n Sua senha foi alterada com sucesso!" +
			"\n\n Abaixo seu N° de Matrícula e a nova senha para acessar o SisEstagio." +
			"\n\n N° de Matrícula: " + aluno.getMatricula() +
			"\n\n Nova Senha: " + pwd +
			"\n\n Atenciosamente," + " \nEquipe SisEstagio";

			SendMailTLS.getInstance().SendMail(vMsg,aluno.getEmail().trim(), "Confirmação de redefinição de senha do SisEstagio");

			MessagesUtil.info("Senha enviada com sucesso! Dentro de alguns minutos, sua senha será enviada ao e-mail cadastrado.");

		} catch (Exception e) {
			MessagesUtil.error("Erro: " + e.getMessage());
		}finally{
			alunoDaoImpl.sessionClose();
		}
	}

	public List<Aluno> getAlunos() {
		alunoDaoImpl.getSession();	
		
		List<Aluno> listaAlunos = alunoDaoImpl.findAll("nome");
		
		alunoDaoImpl.sessionClose();
		
		return listaAlunos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getFilteredAluno() {
		return filteredAluno;
	}

	public void setFilteredAluno(List<Aluno> filteredAluno) {
		this.filteredAluno = filteredAluno;
	}

}