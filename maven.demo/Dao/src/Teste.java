import java.util.*;
import java.lang.Exception;
import java.sql.*;

class Pessoa {
	private String nome;
	private String cpf;
	private int age;

	public Pessoa() {

	}

	public Pessoa(String nome, String cpf, int age) {
		this.nome = nome;
		this.cpf = cpf;
		this.cpf = cpf;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public int getAge() {
		return this.age;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

class Dao {

	private String INSERT_USERS_SQL = "INSERT INTO pessoas.pessoas" + "  (nome, cpf, age) VALUES " + " (?, ?, ?);";
	private String UPDATE_USERS_SQL = "update pessoas.pessoas set nome = ?, cpf = ?, age = ? where cpf = ?;";
	private String DELETE_USERS_SQL = "delete from pessoas.pessoas where cpf = ?;";
	private String SELECT_ALL_QUERY = "select * from pessoas.pessoas";

	public Connection conectar() throws Exception {
		String url = "jdbc:postgresql://localhost/Teste";
		Properties props = new Properties();
		props.setProperty("user", "postgres");
		props.setProperty("password", "885");
		props.setProperty("ssl", "false");
		Connection conn = DriverManager.getConnection(url, props);

		url = "jdbc:postgresql://localhost/Teste?user=postgres&password=885&ssl=false";
		conn = DriverManager.getConnection(url);

		return conn;
	}

	public void create(Connection conexao, Pessoa pessoa) throws SQLException {
		PreparedStatement statement = conexao.prepareStatement(INSERT_USERS_SQL);
		statement.setString(1, pessoa.getNome());
		statement.setString(2, pessoa.getCpf());
		statement.setInt(3, pessoa.getAge());
		System.out.println(statement);
		statement.executeUpdate();
	}

	public void read(Connection conexao) throws SQLException {
		PreparedStatement statement = conexao.prepareStatement(SELECT_ALL_QUERY);
		System.out.println(statement);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			String nome = rs.getString("nome");
			String certificadopessoafisica = rs.getString("cpf");
			int age = rs.getInt("age");
			System.out.println(nome + "," + certificadopessoafisica+ "," + age);
		}
	}

	public void update(Connection conexao, Pessoa pessoa) throws SQLException {
		PreparedStatement statement = conexao.prepareStatement(UPDATE_USERS_SQL);
		statement.setString(1, pessoa.getNome());
		statement.setString(2, pessoa.getCpf());
		statement.setInt(3, pessoa.getAge());
		statement.setString(4, pessoa.getCpf());
		System.out.println(statement);
		statement.executeUpdate();
	}

	public void delete(Connection conexao, String cpf) throws SQLException {
		PreparedStatement statement = conexao.prepareStatement(DELETE_USERS_SQL);
		statement.setString(1, cpf);
		int resul = statement.executeUpdate();
		System.out.println(resul);
	}

}

public class Teste {
	public static void main(String[] args) {
		Dao dao = new Dao();
		try {
			Connection conexao = dao.conectar();
			Pessoa pessoa = new Pessoa("Jolivaldo", "999.999.999-99", 255);
			dao.read(conexao);
			conexao.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
