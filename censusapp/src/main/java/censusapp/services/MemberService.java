package censusapp.services;

import censusapp.Member;

import java.util.List;

public interface MemberService {
    public boolean addMember(Member member);

    public List<Member> getMembers();

}
