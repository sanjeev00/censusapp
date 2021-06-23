package censusapp.services;

import censusapp.Member;
import censusapp.dao.MemberDAO;

import java.util.List;

public class MemberServiceImpl implements  MemberService{
    private MemberDAO memberDAO;

    public  MemberServiceImpl()
    {
        memberDAO = new MemberDAO();
    }

    @Override
    public boolean addMember(Member member) {
        return memberDAO.addMember(member);
    }


    @Override
    public List<Member> getMembers(){
        return memberDAO.getMembers();
    }


}
