package com.yedam.product.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.product.service.ProductService;
import com.yedam.product.vo.CartVO;
import com.yedam.product.vo.ProductVO;

public class ProductServiceImpl extends DAO implements ProductService{

	PreparedStatement psmt;
	ResultSet rs;
	
	@Override
	public List<ProductVO> productSelectList() {
		
		String sql = "select * from product order by 1";
		List<ProductVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemDesc(rs.getString("item_desc"));
				vo.setItemImage(rs.getString("item_image"));
				vo.setItemName(rs.getString("item_name"));
				vo.setLikeIt(rs.getInt("like_it"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale(rs.getString("sale"));
				vo.setSalePrice(rs.getInt("sale_price"));
				
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
	public ProductVO productSelect(ProductVO vo) {
		
		return null;
	}

	@Override
	public int insertProduct(ProductVO vo) {
		
		return 0;
	}

	@Override
	public int updateProduct(ProductVO vo) {
		
		return 0;
	}

	@Override
	public int deleteProduct(ProductVO vo) {
		
		return 0;
	}
	
	// Popular Items 탭
	public List<ProductVO> productFavList(ProductVO vo) {

		String sql = "select * from product where like_it >= 4";
		List<ProductVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getLikeIt());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new ProductVO();
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemDesc(rs.getString("item_desc"));
				vo.setItemImage(rs.getString("item_image"));
				vo.setItemName(rs.getString("item_name"));
				vo.setLikeIt(rs.getInt("like_it"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale(rs.getString("sale"));
				vo.setSalePrice(rs.getInt("sale_price"));
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}
	
	// cart 정보 추가 메소드
	public void addCart(String id, String item, int qty) {
		
		String sql = "insert into cart values(?, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, item);
			psmt.setInt(3, qty);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	// 회원별 장바구니 상품 count
	public int getCountCart(String id) {
		
		String sql = "select count(*) from cart where user_id = ?";
		int rCnt = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				rCnt = rs.getInt(1);  // n번째 Column 가져오는 방법. n 적으면 가능
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rCnt;
	}
	
	// 장바구니 조회
	public List<CartVO> getCartList(String id) {
		
		String sql = "select * from (\r\n"
				+ "select *\r\n"
				+ "from (select user_id, item_code, sum(item_qty) from cart group by user_id, item_code) A JOIN (select * from product) B\r\n"
				+ "ON A.item_code = B.item_code)\r\n"
				+ "where user_id = ?";
		List<CartVO> list = new ArrayList<>();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CartVO vo = new CartVO();
				vo.setId(id);
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemQty(rs.getInt("sum(item_qty)"));
				vo.setItemImage(rs.getString("item_image"));
				vo.setSalePrice(rs.getInt("sale_price"));
				vo.setItemName(rs.getString("item_name"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
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
}
