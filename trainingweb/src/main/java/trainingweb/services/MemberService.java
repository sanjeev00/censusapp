package trainingweb.services;

import trainingweb.entities.Member;

import java.util.List;

public interface MemberService {
    public boolean addMember(Member member);

    public List<Member> getMembers();

}
