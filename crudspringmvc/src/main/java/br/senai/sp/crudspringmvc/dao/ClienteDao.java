package br.senai.sp.crudspringmvc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.crudspringmvc.model.Cliente;
import br.senai.sp.crudspringmvc.model.Genero;


public class ClienteDao {
	private Connection conexao;

	public ClienteDao() {
		conexao = ConnectionFactory.conectar();

	}

	public void inserir(Cliente cliente) {
		String sql = "insert into tb_cliente" + "(nome, data_nascimento, genero, endereco, telefone, email, produto_interesse)"
				+ "values(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt;

		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setDate(2, new Date(cliente.getDataNascimento().getTimeInMillis()));
			stmt.setInt(3, cliente.getGenero().ordinal());
			stmt.setString(4, cliente.getEndereco());
			stmt.setString(5, cliente.getTelefone());
			stmt.setString(6, cliente.getEmail());
			stmt.setString(7, cliente.getProdInteresse());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> listar() {
		String sql = "select * from tb_cliente order by nome asc";
		PreparedStatement stmt;
		List<Cliente> lista = new ArrayList<Cliente>();

		try {
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
		
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setEndereco(rs.getString("endereco"));
				c.setTelefone(rs.getString("telefone"));
				c.setEmail(rs.getString("email"));
				c.setProdInteresse(rs.getString("produto_interesse"));

				Calendar nascimento = Calendar.getInstance();

				Date nascDB = rs.getDate("data_nascimento");

				nascimento.setTimeInMillis(nascDB.getTime());

				c.setDataNascimento(nascimento);
				
				int posEnum = rs.getInt("genero");
				
				// Obter e "setar" o TamanhoUniforme de acordo com a posEnum
				c.setGenero(Genero.values()[posEnum]);
				lista.add(c);
			}
			
			
			
			rs.close();
			stmt.close();
			conexao.close();
			return lista;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(long idCliente) {
		String sql = "delete from tb_cliente where id = ?";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, idCliente);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
	}

	public void atualizar(Cliente cliente) {
		String sql = "update tb_cliente set nome = ?, data_nascimento = ?, genero = ?, endereco = ?, telefone = ?, "
				+ "email = ?, produto_interesse = ? where id = ?";

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setDate(2, new Date(cliente.getDataNascimento().getTimeInMillis()));
			stmt.setInt(3, cliente.getGenero().ordinal());
			stmt.setString(4, cliente.getEndereco());
			stmt.setString(5, cliente.getTelefone());
			stmt.setString(6, cliente.getEmail());
			stmt.setString(7, cliente.getProdInteresse());
			stmt.setLong(8, cliente.getId());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente buscar(long idCliente) {
		String sql = "select * from tb_cliente where id = ?";
		PreparedStatement stmt;
		Cliente c = null;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setLong(1, idCliente);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				c = new Cliente();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setEndereco(rs.getString("endereco"));
				c.setTelefone(rs.getString("telefone"));
				c.setEmail(rs.getString("email"));
				c.setProdInteresse(rs.getString("produto_interesse"));

				Calendar nascimento = Calendar.getInstance();

				Date nascDB = rs.getDate("data_nascimento");

				nascimento.setTimeInMillis(nascDB.getTime());

				c.setDataNascimento(nascimento);
				
				int posEnum = rs.getInt("genero");
				
				c.setGenero(Genero.values()[posEnum]);

			}

			rs.close();
			stmt.close();
			conexao.close();
			return c;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
