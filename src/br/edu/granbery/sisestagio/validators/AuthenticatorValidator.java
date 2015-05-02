package br.edu.granbery.sisestagio.validators;

import br.edu.granbery.sisestagio.dao.AlunoDaoImpl;
import br.edu.granbery.sisestagio.dao.UsuarioDaoImpl;
import br.edu.granbery.sisestagio.model.Aluno;
import br.edu.granbery.sisestagio.model.Usuario;

public class AuthenticatorValidator {

	private static AuthenticatorValidator instance = new AuthenticatorValidator();

	public static AuthenticatorValidator getInstance() {
		return instance;
	}

	public Usuario validateLogin(String email, String senha) throws Exception {
		UsuarioDaoImpl usuarioDaoImpl = new UsuarioDaoImpl();
		Usuario usuario = new Usuario();

		usuarioDaoImpl.getSession();
		usuario = usuarioDaoImpl.login(email, senha);
		usuarioDaoImpl.sessionClose();

		if (usuario == null) {
			throw new Exception();
		}

		return usuario;
	}

	public Aluno validateAlunoConsulta(String matricula, String senha)
			throws Exception {
		AlunoDaoImpl alunoDaoImpl = new AlunoDaoImpl();
		Aluno aluno = new Aluno();

		alunoDaoImpl.getSession();
		aluno = alunoDaoImpl.findFields("senha", senha, "matricula", matricula);
		alunoDaoImpl.sessionClose();

		if (aluno == null) {
			throw new Exception(
					"O N° de Matrícula e/ou a Senha estão incorretos.");
		}

		return aluno;
	}

	public Aluno validateEsqueciMinhaSenha(String matricula, String email) throws Exception {
		AlunoDaoImpl alunoDaoImpl = new AlunoDaoImpl();
		Aluno aluno = new Aluno();

		alunoDaoImpl.getSession();
		aluno = alunoDaoImpl.findFields("email", email, "matricula", matricula);
		alunoDaoImpl.sessionClose();

		if (aluno == null) {
			throw new 
			  Exception("Nenhum Aluno com esse N° de Matrícula foi encontrado no E-mail procurado. Verifique e informe os dados novamente.");
		}

		return aluno;
	}
}
