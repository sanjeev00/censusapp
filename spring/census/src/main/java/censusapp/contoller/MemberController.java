package censusapp.contoller;
import censusapp.entities.Member;
import censusapp.entities.MemberRepository;
import censusapp.exceptions.InvalidMemberException;
import censusapp.exceptions.MemberNotFoundException;
import censusapp.services.MemberService;
import censusapp.services.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import  org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/member")
    List<Member> listMembers()
    {
        MemberService memberService = new MemberServiceImpl();
        return memberService.getMembers(memberRepository);
    }

    @GetMapping("/member/{memberId}")
    Member getMember(@PathVariable String memberId) throws MemberNotFoundException {
        MemberService memberService = new MemberServiceImpl();
        Member member =  memberService.getMember(memberRepository,memberId);
        if(member==null)
            throw new MemberNotFoundException();
        return member;
    }


    @PostMapping("/member")
    Member addMember( @Valid Member member,BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors())
            throw new InvalidMemberException(bindingResult.getAllErrors());
        MemberService memberService = new MemberServiceImpl();
        return memberService.saveMember(memberRepository, member);

    }


}
