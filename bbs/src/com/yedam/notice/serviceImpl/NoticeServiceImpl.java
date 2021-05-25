package com.yedam.notice.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.vo.NoticeVO;

public class NoticeServiceImpl extends DAO implements NoticeService {
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
	
	public List<NoticeVO> noticeListPaging(int page){
		// 글목록 페이징 메소드
		String sql="select b.* \r\n" //
				+ "from( select rownum rn, a.* \r\n" //
				+ "      from (select * from notice order by id desc)a\r\n" //
				+ "      )b\r\n" //
				+ "where b.rn between ? and ?";
		
		List<NoticeVO> list = new ArrayList<NoticeVO>();
	
		// 한 페이지당 10건씩
		int firstCnt, lastCnt = 0;
		firstCnt = (page - 1) * 10 + 1;	// 1
		lastCnt = (page * 10);	// 10
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, firstCnt);
			psmt.setInt(2, lastCnt);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setHit(rs.getInt("hit"));
				vo.setContent(rs.getString("content"));
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
	public List<NoticeVO> noticeSelectList() {
		String sql = "select * from notice order by 1 desc";
		List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setHit(rs.getInt("hit"));
				noticeList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return noticeList;
	}
	
	public void hitCount(int id) {
		String sql = "update notice set hit = hit+1 where id = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public NoticeVO noticeSelect(NoticeVO vo) {
		String sql = "select * from notice where id = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				hitCount(vo.getId());	// 조회수 증가
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}

	@Override
	public int insertNotice(NoticeVO vo) {
		String sql = "insert into notice values(notice_seq.nextval,?,?,sysdate,0)";
		
		int in = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			in = psmt.executeUpdate();
			System.out.println(in+"건 입력완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return in;
	}

	@Override
	public int updateNotice(NoticeVO vo) {
		String sql = "update notice set title=?, content=? where id = ?";
		int up = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getId());
			
			up = psmt.executeUpdate();
			System.out.println(up + "건 수정완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return up;
	}

	@Override
	public int deleteNotice(NoticeVO vo) {

		return 0;
	}

}
