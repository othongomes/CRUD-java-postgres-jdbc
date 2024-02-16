package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	// MÉTODO INSERT
	public void salvar(Userposjava userposjava) {
		try {
			String sql = "insert into userposjava (nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback(); // reverse the operation
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// MÉTODO SELECT PARA LISTAR
	public List<Userposjava> listar() throws SQLException {
		List<Userposjava> list = new ArrayList<Userposjava>();

		String sql = "select * from userposjava";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

			list.add(userposjava);

		}

		return list;
	}

	// MÉTODO SELECT PARA CONSULTAR APENAS UM OBJETO
	public Userposjava listaruM(Long id) throws SQLException {
		Userposjava retorno = new Userposjava();

		String sql = "select * from userposjava where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { // return only one or none
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}
		return retorno;
	}

	// MÉTODO UPDATE PARA ATUALIZAR DADOS
	public void atualizar(Userposjava userposjava) {

		String sql = "update userposjava set nome = ? where id = " + userposjava.getId();

		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());

			statement.execute();
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	// MÉTODO DELETE PARA DELETAR DADOS
	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	// __________________DAO PARA TABELA LISTA DE TELEFONES___________________

	// MÉTODO INSERT
	public void salvarTelefone(Telefone telefone) {
		try {
			String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuariopessoa());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback(); // reverse the operation
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// MÉTODO SELECT PARA BUSCAR POR ID COM INNER JOIN
	public List<BeanUserFone> listarInnerJoin(Long idUser) throws SQLException {

		List<BeanUserFone> list = new ArrayList<BeanUserFone>();

		String sql = "select nome, numero, email from telefoneuser as fone\r\n" + "inner join userposjava as userp\r\n"
				+ "on fone.usuariopessoa = userp.id\r\n" + "where userp.id =" + idUser;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				BeanUserFone userfone = new BeanUserFone();
				userfone.setNome(resultado.getString("nome"));
				userfone.setNumero(resultado.getString("numero"));
				userfone.setEmail(resultado.getString("email"));

				list.add(userfone);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	// MÉTODO DELETE PARA DELETAR DADOS EM CASCATA
		public void deletarFonesPorUser (Long id) {
			try {
				String sqlFone = "delete from telefoneuser where usuariopessoa = " + id;
				String sqlUser = "delete from userposjava where id = " + id;
				
				PreparedStatement preparedStatementFone = connection.prepareStatement(sqlFone);
				preparedStatementFone.executeUpdate();
				connection.commit();
				
				PreparedStatement preparedStatementUser = connection.prepareStatement(sqlUser);
				preparedStatementUser.executeUpdate();
				connection.commit();
				
			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}

}
