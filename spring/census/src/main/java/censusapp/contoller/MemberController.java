package censusapp.contoller;
import censusapp.entities.Member;
import censusapp.entities.MemberRepository;
import censusapp.exceptions.InvalidMemberException;
import censusapp.exceptions.MemberNotFoundException;
import censusapp.services.MemberService;
import censusapp.services.MemberServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import  org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;
    final static Logger logger = LogManager.getLogger(MemberController.class);


    @GetMapping("/member")
    List<Member> listMembers(@RequestHeader("application_id") @Nullable String applicationId)
    {
        logger.info("REST API invoked");
        if(applicationId==null || applicationId.equals("null"))
            return null;
        return memberService.getMembers(memberRepository,applicationId);
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
    Member addMember(@RequestHeader("application_id") @Nullable String applicationId, @RequestBody  Member member, BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors())
            throw new InvalidMemberException(bindingResult.getAllErrors());

        if(applicationId==null || applicationId.equals("null")|| applicationId.equals(""))
            return memberService.addHeadMember(memberRepository, member);
        return memberService.addMember(memberRepository,member,applicationId);
    }

    @PutMapping("/member")
    Member editMember(@RequestHeader("application_id") @Nullable String applicationId, @RequestBody  Member member, BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors())
            throw new InvalidMemberException(bindingResult.getAllErrors());
        return memberService.editMember(memberRepository,member,applicationId);
    }



    @DeleteMapping("/member/{id}")
    String deleteMember(@PathVariable String id)
    {
        if(memberService.deleteMember(memberRepository,id))
            return "Member deleted";
        else
            return "Unable to delete Member";
    }


}
