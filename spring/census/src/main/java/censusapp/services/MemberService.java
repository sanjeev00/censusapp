package censusapp.services;

import censusapp.entities.Member;
import censusapp.entities.MemberRepository;

import java.util.List;

public interface MemberService {
    public Member addHeadMember(MemberRepository memberRepository,Member member) throws Exception;

    public Member addMember(MemberRepository memberRepository,Member member,String applicationId) throws Exception ;

    public List<Member> getMembers(MemberRepository memberRepository,String applicationId);

    public Member getMember(MemberRepository memberRepository,String id);

    public Member editMember(MemberRepository memberRepository,Member member,String applicationId) throws Exception;

    public boolean deleteMember(MemberRepository memberRepository,String id);

}
