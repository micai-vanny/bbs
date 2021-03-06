package com.yedam.notice.service;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;

public interface NoticeService {
	// 조회, 한 건 조회, 입력, 수정, 삭제
	List<NoticeVO> noticeSelectList();
	NoticeVO noticeSelect(NoticeVO vo);
	int insertNotice(NoticeVO vo);
	int updateNotice(NoticeVO vo);
	int deleteNotice(NoticeVO vo);
}
