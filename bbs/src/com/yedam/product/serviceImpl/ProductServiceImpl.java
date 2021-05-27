package com.yedam.product.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.product.service.ProductService;
import com.yedam.product.vo.ProductVO;

public class ProductServiceImpl extends DAO implements ProductService {
	PreparedStatement psmt;
	ResultSet rs;
	
	// cart 정보 추가하는 메소드
	public void addCart(String id, String itemCode, int qty) {
		String sql = "insert into cart values(?,?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, itemCode);
			psmt.setInt(3, qty);
			int add = psmt.executeUpdate();
			System.out.println(add+"건 추가완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	// cart 안에 뭐 들었는지 보여주자
	public List<ProductVO> selectCart(String id) {
		String sql = "select * from\r\n"
				+ "(select user_id, item_code, sum(item_qty) qty from cart group by user_id, item_code) cart, product p\r\n"
				+ "where cart.item_code = p.item_code\r\n"
				+ "and cart.user_id = ?";
		List<ProductVO> cartList = new ArrayList<ProductVO>();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setUserId(rs.getString("user_id"));
				vo.setItemCode(rs.getString("item_code"));
				vo.setQty(rs.getInt("qty"));
				vo.setItemName(rs.getString("item_name"));
				vo.setItemImage(rs.getString("item_image"));
				vo.setPrice(rs.getInt("price"));
				vo.setSalePrice(rs.getInt("sale_price"));
				vo.setSale(rs.getString("sale"));
				cartList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cartList;
	}
	
	// 로그인 한 id에 맞게 장바구니에 담아둔 상품의 갯수 보여줌
	public int getCountCart(String id) {
		String sql = "select count(*) from cart where user_id=?";
		int rCnt = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				rCnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}// 카트에 담긴 갯수를 리턴.
		return rCnt;
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

	@Override
	public List<ProductVO> productSelectList() {
		String sql = "select * from product order by 1";
		List<ProductVO> productList = new ArrayList<ProductVO>();
		
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
				
				productList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return productList;
	}

	@Override
	public ProductVO productSelect(ProductVO vo) {
		String sql = "select * from product where item_code = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getItemCode());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemDesc(rs.getString("item_desc"));
				vo.setItemImage(rs.getString("item_image"));
				vo.setItemName(rs.getString("item_name"));
				vo.setLikeIt(rs.getInt("like_it"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale(rs.getString("sale"));
				vo.setSalePrice(rs.getInt("sale_price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int insertProduct(ProductVO vo) {
		String sql = "insert into product values(?,?,?,?,?,?,?,?)";
		int in = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getItemCode());
			psmt.setString(2, vo.getItemDesc());
			psmt.setString(3, vo.getItemImage());
			psmt.setString(4, vo.getItemName());
			psmt.setString(5, vo.getSale());
			psmt.setInt(6, vo.getLikeIt());
			psmt.setInt(7, vo.getPrice());
			psmt.setInt(8, vo.getSalePrice());
			
			in = psmt.executeUpdate();
			System.out.println(in + "건 입력완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return in;
	}

	@Override
	public int updatetProduct(ProductVO vo) {
		String sql = "update product set item_image = ?, item_desc = ?, sale = ?, sale_price = ? where item_code = ? ";
		return 0;
	}

	@Override
	public int deletetProduct(ProductVO vo) {
		
		return 0;
	}

}
