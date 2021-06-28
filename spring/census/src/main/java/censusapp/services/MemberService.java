package censusapp.services;

import censusapp.entities.Member;
import censusapp.entities.MemberRepository;

import java.util.List;

public interface MemberService {
    public Member saveMember(MemberRepository memberRepository,Member member) ;

    public List<Member> getMembers(MemberRepository memberRepository);

    public Member getMember(MemberRepository memberRepository,String id);


}
