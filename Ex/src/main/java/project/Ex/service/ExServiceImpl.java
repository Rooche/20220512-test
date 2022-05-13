package project.Ex.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.Ex.dao.DataSource;

public class ExServiceImpl implements ExService {

	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public void insertEx(ExVO vo) {
		String sql = "INSERT INTO EX_LIST VALUES(?, ?, ?, ?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareCall(sql);
			psmt.setInt(1, vo.getNum());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getWriter());
			psmt.setString(4, vo.getContent());

			int result = psmt.executeUpdate();
			if (result > 0) {
				System.out.println("정상적으로 등록되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	@Override
	public int updateEx(ExVO vo) {
		int n = 0;
		String sql = "UPDATE EX_LIST SET CONTENT = ? WHERE NUM = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getContent());
			psmt.setInt(2, vo.getNum());
			;
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public void deleteEx(ExVO vo) {
		int n = 0;
		String sql = "DELETE FROM EX_LIST WHERE NUM = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getNum());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<ExVO> selectExList(String keyword) {
		List<ExVO> Exs = new ArrayList<ExVO>();
		ExVO vo;
		String sql = "SELECT * FROM EX_LIST WHERE CONTENT LIKE '%'||?||'%'";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, keyword); // 물음표가 1개니깐 1로 적어둔거임
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new ExVO();
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));

				Exs.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return Exs;
	}

	@Override
	public List<ExVO> selectAllList() {
		List<ExVO> Exs = new ArrayList<ExVO>();
		ExVO vo;
		String sql = "SELECT * FROM EX_LIST";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new ExVO();
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));

				Exs.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return Exs;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
