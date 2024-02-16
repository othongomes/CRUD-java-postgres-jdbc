package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TestBancoJdbc {

	// INSERT
	@Test
	public void initBanco() {
		SingleConnection.getConnection();
		UserPosDAO userPosDAo = new UserPosDAO();
		Userposjava userPosjava = new Userposjava();

		userPosjava.setNome("jereba");
		userPosjava.setEmail("jereba@gmail.com");

		userPosDAo.salvar(userPosjava);
	}

	// SELECT (LISTA)
	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.listar();

			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
				System.out.println("--------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SELECT (UM OBJETO)
	@Test
	public void initListarUm() {
		UserPosDAO dao = new UserPosDAO();
		try {
			Userposjava userposjava = dao.listaruM(2L);
			System.out.println(userposjava);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// UPDATE
	@Test
	public void initAtualizar() {

		UserPosDAO dao = new UserPosDAO();

		try {
			Userposjava objetoBanco = dao.listaruM(3L);

			objetoBanco.setNome("Updated name");

			dao.atualizar(objetoBanco);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// DELETE
	@Test
	public void initDeletar() {

		try {
			UserPosDAO dao = new UserPosDAO();
			dao.deletar(1L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ______ DAO PARA LISTA DE TELEFONES__________

	// INSERT
	@Test
	public void initBancoTelefone() {
		SingleConnection.getConnection();
		UserPosDAO userPosDAo = new UserPosDAO();
		Telefone telefone = new Telefone();

		telefone.setNumero("(16) 9977-9999");
		telefone.setTipo("celular");
		telefone.setUsuariopessoa(9L);

		userPosDAo.salvarTelefone(telefone);
	}

	// SELECT LISTA INNER JOIN
	@Test
	public void initListaInnerjoin() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<BeanUserFone> list = dao.listarInnerJoin(9L);

			for (BeanUserFone beanUserFone : list) {
				System.out.println(beanUserFone);
				System.out.println("--------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DELETE EM CASCATA
	@Test
	public void initDeletarCascata() {

		try {
			UserPosDAO dao = new UserPosDAO();
			dao.deletarFonesPorUser(9L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
