package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;

import com.jsp.dao.MemberDAO;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.exception.NullidException;
import com.jsp.exception.NullpasswordException;

public class MemberServiceImpl implements MemberService {

	// 싱글톤 패턴 구현
	private static MemberServiceImpl instance = new MemberServiceImpl();
	private MemberServiceImpl() {}
	public static MemberServiceImpl getInstance() {
		return instance;
	}
	
	private MemberDAO memberDAO;//=MemberDAOImpl.getInstance();
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO=memberDAO;
	}
	
	
	@Override
	public void login(String id, String pwd) throws SQLException, NotFoundIDException, InvalidPasswordException, NullidException, NullpasswordException {
		MemberVO member = memberDAO.selectMemberById(id);
		if(id.length()==0) throw new NullidException();
		if(pwd.length()==0) throw new NullpasswordException();
		if(member == null) throw new NotFoundIDException();
		if(!pwd.equals(member.getPwd())) throw new InvalidPasswordException();
		
	}

	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		List<MemberVO> member = memberDAO.selectMemberList();
		return member;
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		MemberVO member = memberDAO.selectMemberById(id);
		return member;
	}

	@Override
	public void regist(MemberVO member) throws SQLException {
		memberDAO.insertMember(member);
	}

	@Override
	public void modify(MemberVO member) throws SQLException {
		memberDAO.updateMember(member);
	}

	@Override
	public void remove(String id) throws SQLException {
		memberDAO.deleteMember(id);
	}
	@Override
	public void disabled(String id) throws SQLException {
		memberDAO.disabledMember(id);
		
	}
	@Override
	public void enabled(String id) throws SQLException {
		memberDAO.enabledMember(id);
		
	}

}
