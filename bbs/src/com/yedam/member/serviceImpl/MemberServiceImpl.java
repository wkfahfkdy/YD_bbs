package com.yedam.member.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.member.service.MemberService;
import com.yedam.member.vo.MemberVO;

public class MemberServiceImpl extends DAO implements MemberService{

	PreparedStatement psmt;
	ResultSet rs;
	
	// id, passwd 체크 method
	public MemberVO loginCheck(MemberVO vo) {
		
		String sql = "select * from member where id = ? and passwd = ?";
		MemberVO rvo = null; // MemberLogin.java 에서 쓰기 위해 null 값 잡음
							 // null 인 이유. 로그인을 못함 = sql문 실행못함 = null 값 > 로그인 실패 판결
							 // boolean type 으로 한다면 기본값을 false 로 잡고 로그인 했을 시 true로 한 후
							 // MemberLogin.java 에서 true 값을 불러옴 = 로그인 성공 처리
		
		try {
			psmt = conn.prepareCall(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPwd());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				rvo = new MemberVO();
				rvo.setId(rs.getString("id"));
				rvo.setPwd(rs.getString("passwd"));
				rvo.setName(rs.getString("name"));
				rvo.setAddr(rs.getString("address"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return rvo;
	}
	
	
	// id 중복 체크 method - 중복 존재 시 true / 없을 시 false
	public boolean idCheck(String id) {
		boolean exist = false;
		String sql = "select id from member where id = ?";
		
		try {
			psmt = conn.prepareCall(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				exist = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return exist;
	}
	
	@Override
	public List<MemberVO> selectMemberList() {
		return null;
	}

	@Override
	public MemberVO selectMember() {
		return null;
	}

	@Override
	public int insertMember(MemberVO vo) {
		String sql = "insert into member(id, name, passwd, address) values(?, ?, ?, ?)";
		int r = 0;
		try {
			psmt = conn.prepareCall(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPwd());
			psmt.setString(4, vo.getAddr());
			r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	private void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public int updateMember(MemberVO vo) {
		return 0;
	}

	@Override
	public int deleteMember(MemberVO vo) {
		
		return 0;
	}

}
