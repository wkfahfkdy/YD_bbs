package com.yedam.bulletin.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DAO;

public class BulletinServiceImpl extends DAO implements BulletinService {
	
	PreparedStatement psmt;
	ResultSet rs;
	
	@Override
	public List<BulletinVO> bulletinSelectList() {
		
		String sql = "select * from bulletin order by 1";
		
		List<BulletinVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BulletinVO vo = new BulletinVO();
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setId(rs.getInt("id"));
				vo.setRegDate(rs.getDate("reg_Date"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public BulletinVO bulletinSelect(BulletinVO vo) {
		
		String sql = "select * from bulletin where id = ?";
		
		try {
			psmt = conn.prepareCall(sql);
			psmt.setInt(1, vo.getId());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setRegDate(rs.getDate("reg_Date"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}

	@Override
	public int insertBulletin(BulletinVO vo) {
		
		String sql = "insert into bulletin values(bulletin_seq.nextval, ?, ?, ?, sysdate, 0)";
		int r = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}

	@Override
	public int updateBulletin(BulletinVO vo) {
		
		String sql = "update bulletin set title = ?, content = ? where id = ?";
		int r = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getId());
			r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	@Override
	public int deleteBulletin(BulletinVO vo) {
		
		String sql = "delete from bulletin where id = ?";
		int r = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			close();
		}
		
		return r;
	}
	
	// hit 갱신 -> 게시글 들어갈때 실행시키는 것이 아니라 select sql 를 실행시킬 때 같이 해버림
	public void hitCount(int id) {
		
		String sql = "update bulletin set hit = hit + 1 where id = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

	private void close() {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<BulletinVO> bulletinListPaging(int page) {
		
		String sql = "select b.*\r\n" //
				+ "from ( select rownum rn, a.*\r\n" //
				+ "      from ( select * from bulletin order by id) a\r\n" //
				+ "      ) b\r\n" //
				+ "where b.rn between ? and ?"; //
		
		int firstCnt = 0, lastCnt = 0;
		firstCnt = (page - 1) * 10 + 1; // = 1, 11, 21 ...
		lastCnt = (page * 10); // = 10, 20, 30 ...
		
		List<BulletinVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, firstCnt);
			psmt.setInt(2, lastCnt);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BulletinVO vo = new BulletinVO();
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setId(rs.getInt("id"));
				vo.setRegDate(rs.getDate("reg_Date"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}
}
