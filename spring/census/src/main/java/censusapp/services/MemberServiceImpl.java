package censusapp.services;

import censusapp.entities.Member;
import censusapp.entities.MemberRepository;


import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MemberServiceImpl implements  MemberService{

    private MemberRepository memberRepository;
    public  MemberServiceImpl()
    {

    }

    @Override
    public Member saveMember(MemberRepository memberRepository,Member member){
        member.setMemberId(String.valueOf((new Random()).nextLong()));

        Member result = memberRepository.save(member);
        return result;
    }


    @Override
    public List<Member> getMembers(MemberRepository memberRepository){
        return memberRepository.findAll();
    }

    @Override
    public Member getMember(MemberRepository memberRepository, String id){

        Optional<Member> member =  memberRepository.findByMemberId(id);

        return member.orElse(null);
    }


}
