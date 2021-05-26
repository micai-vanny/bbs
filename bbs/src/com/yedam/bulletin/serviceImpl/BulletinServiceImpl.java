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
	
	public List<BulletinVO> bulletinListPaging(int page){
		String sql = "select b.* \r\n" //
				+ "from( select rownum rn, a.* \r\n" //
				+ "      from (select * from bulletin order by id desc)a\r\n" //
				+ "      )b\r\n" //
				+ "where b.rn between ? and ?";
		
		List<BulletinVO> list = new ArrayList<BulletinVO>();
		
		// 한 페이지 당 10건씩 노출
		int firstCnt, lastCnt = 0;
		
		firstCnt = (page - 1) * 10 + 1;
		lastCnt = (page * 10);
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, firstCnt);
			psmt.setInt(2, lastCnt);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BulletinVO vo = new BulletinVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setHit(rs.getInt("hit"));
				
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
	public List<BulletinVO> bulletinSelectList() {
		String sql = "select * from bulletin order by 1 desc";
		List<BulletinVO> bulletinList = new ArrayList<BulletinVO>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BulletinVO vo = new BulletinVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setHit(rs.getInt("hit"));
				bulletinList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bulletinList;
	}
	
	public void hitCount(int id) {
		String sql = "update bulletin set hit = hit+1 where id = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	public BulletinVO bulletinSelect(BulletinVO vo) {
		String sql = "select * from bulletin where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				hitCount(vo.getId());
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setHit(rs.getInt("hit"));
				vo.setContent(rs.getString("content"));
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
		String sql = "insert into bulletin values(bulletin_seq.nextval,?,?,?,sysdate,0)";
		
		int in = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			
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
	public int updateBulletin(BulletinVO vo) {
		String sql = "update bulletin set title = ?, content = ? where id = ?";
		int up = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getId());
			
			up = psmt.executeUpdate();
			System.out.println(up + "건 수정완료");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return up;
	}
	
	@Override
	public int deleteBulletin(BulletinVO vo) {
		String sql = "delete from bulletin where id = ?";
		int del = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			
			del = psmt.executeUpdate();
			System.out.println(del + "건 삭제완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0;
	}
	
	
}
