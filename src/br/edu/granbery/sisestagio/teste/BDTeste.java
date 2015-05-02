package br.edu.granbery.sisestagio.teste;

import org.hibernate.Session;

import br.edu.granbery.sisestagio.model.Usuario;
import br.edu.granbery.sisestagio.util.HibernateUtil;

public class BDTeste {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();

		session.beginTransaction();

		Usuario usuarioTeste = new Usuario();
		usuarioTeste.setIdUsuario(1);
		usuarioTeste.setEmail("admin");
		usuarioTeste.setNome("Administrado");
		usuarioTeste.setSenha("12345");

		session.save(usuarioTeste);

		session.getTransaction().commit();

		session.close();

		System.out.println("Salvo com sucesso no banco de dados");

	}

}
