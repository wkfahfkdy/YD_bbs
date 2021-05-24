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
