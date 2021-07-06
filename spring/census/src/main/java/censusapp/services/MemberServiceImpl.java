package censusapp.services;

import censusapp.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MemberServiceImpl implements  MemberService{

    private MemberRepository memberRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public  MemberServiceImpl()
    {

    }

    @Override
    public Member addHeadMember(MemberRepository memberRepository, Member member) throws Exception {
        MemberValidator memberValidator =new MemberValidator(member);
        memberValidator.validate();
        Application application = new Application("PENDING",String.valueOf((new Random()).nextLong()));
        application = applicationRepository.save(application);
        member.setApplication(application);
        member.setIsHead(true);
        Member result = memberRepository.save(member);
        return result;
    }

    @Override
    public Member addMember(MemberRepository memberRepository,Member member,String applicationId) throws Exception{
        MemberValidator memberValidator =new MemberValidator(member);
        Optional<Application> application = applicationRepository.findById(applicationId);
        if(application.isPresent()) {
            memberValidator.validate();
            member.setIsHead(false);
            member.setApplication(application.get());
            Member result = memberRepository.save(member);
            return result;
        }
        return null;
    }

    public Member editMember(MemberRepository memberRepository,Member member,String applicationId) throws Exception
    {
        Optional<Member> dbMember = memberRepository.findByMemberId(member.getMemberId());
        MemberValidator memberValidator =new MemberValidator(member);
        Optional<Application> application = applicationRepository.findById(applicationId);
        if(application.isPresent() && dbMember.isPresent()) {
            memberValidator.validate();
            member.setApplication(application.get());
            member.setIsHead(dbMember.get().getIsHead());
            Member result = memberRepository.save(member);
            return result;
        }
        return null;
    }



    @Override
    public List<Member> getMembers(MemberRepository memberRepository,String applicationId){
        return memberRepository.findByApplication(applicationId);
    }

    @Override
    public Member getMember(MemberRepository memberRepository, String id){

        Optional<Member> member =  memberRepository.findByMemberId(id);

        return member.orElse(null);
    }

    @Override
    public boolean deleteMember(MemberRepository memberRepository,String id)
    {
        memberRepository.deleteById(id);
        return true;
    }


}
