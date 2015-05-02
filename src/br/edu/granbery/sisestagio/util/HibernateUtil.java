package br.edu.granbery.sisestagio.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.edu.granbery.sisestagio.model.Aluno;
import br.edu.granbery.sisestagio.model.Curriculo;
import br.edu.granbery.sisestagio.model.Distrato;
import br.edu.granbery.sisestagio.model.Documento;
import br.edu.granbery.sisestagio.model.Empresa;
import br.edu.granbery.sisestagio.model.ProjetoEstagio;
import br.edu.granbery.sisestagio.model.ProjetoFinal;
import br.edu.granbery.sisestagio.model.RelatorioAcompanhamento;
import br.edu.granbery.sisestagio.model.RelatorioMensal;
import br.edu.granbery.sisestagio.model.TermoAditivo;
import br.edu.granbery.sisestagio.model.TermoCompromisso;
import br.edu.granbery.sisestagio.model.Usuario;

public class HibernateUtil {
	private static SessionFactory factory;

	static {
		AnnotationConfiguration conf = new AnnotationConfiguration();

		// Configurações do Banco de Dados
		conf.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		conf.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		conf.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sisestagio?autoReconnect=true&createDatabaseIfNotExist=true");
	    conf.setProperty("hibernate.connection.username", "root");
		//conf.setProperty("hibernate.connection.password", "a6u1e4");
	    conf.setProperty("hibernate.connection.password", "123");
		conf.setProperty("hibernate.hbm2ddl.auto", "update");

		conf.setProperty("hibernate.show_sql", "false");
		conf.setProperty("hibernate.use_sql_comments", "false");
		conf.setProperty("hibernate.generate_statistics", "false");
		conf.setProperty("format_sql", "false");

		// Mapeando as classes anotadas
		conf.addAnnotatedClass(Aluno.class);
		conf.addAnnotatedClass(Curriculo.class);
		conf.addAnnotatedClass(Distrato.class);
		conf.addAnnotatedClass(Documento.class);
		conf.addAnnotatedClass(Empresa.class);
		conf.addAnnotatedClass(ProjetoEstagio.class);
		conf.addAnnotatedClass(ProjetoFinal.class);
		conf.addAnnotatedClass(RelatorioAcompanhamento.class);
		conf.addAnnotatedClass(RelatorioMensal.class);
		conf.addAnnotatedClass(TermoAditivo.class);
		conf.addAnnotatedClass(TermoCompromisso.class);
		conf.addAnnotatedClass(Usuario.class);

		factory = conf.buildSessionFactory();

	}

	public static Session getSession() {
		return factory.openSession();
	}

}
