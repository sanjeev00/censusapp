package censusapp;

import censusapp.dao.MemberDAO;
import censusapp.services.MemberService;
import censusapp.services.MemberServiceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CliHelper {
    private MemberService memberService;

    public CliHelper()
    {
       this.memberService = new MemberServiceImpl();
    }

    public void addMember(boolean head)
    {
        Scanner sc = new Scanner(System.in);

        String fName,lName,mName,gender,dob;
        System.out.print("First Name : ");
        fName = sc.next();

        System.out.print("Middle Name : ");
        mName = sc.next();


        System.out.print("Last Name : ");
        lName = sc.next();

        System.out.print("Date of birth (YYYY-MM-DD) : ");
        dob = sc.next();

        System.out.print("Gender (Male|Female|Other): ");
        gender = sc.next();



        try {
            Member member = new Member();

            member.setFirstName(fName);
            member.setLastName(lName);
            member.setMiddleName(mName);
            member.setDateOfBirth(dob);
            member.setGender(gender);
            member.setIsHead(head);
            member.setMemberId(randomIdGenerator());

            memberService.addMember(member);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void getMembers()
    {

        List<Member> members = memberService.getMembers();
        //Iterator itr = members.listIterator();
        int memberCount = 0;
        /*while(itr.hasNext())
        {
            memberCount++;
            System.out.println("\n\nMember "+memberCount );
            Member member  =(Member)itr.next();
            System.out.print("Id : "+member.getFirstName() );
            if(member.getIsHead())
                System.out.print("\tHead of Family");
            System.out.println("\nfirst Name: "+member.getFirstName() );
            System.out.println("middle Name: "+member.getMiddleName() );
            System.out.println("last Name: "+member.getLastName() );
            System.out.println("gender: "+member.getGender() );
            System.out.println("dob: "+member.getDateOfBirth() );


        }*/
    }

    public String randomIdGenerator()
    {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

}
