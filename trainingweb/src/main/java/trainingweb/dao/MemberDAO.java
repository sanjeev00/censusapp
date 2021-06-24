package trainingweb.dao;

import trainingweb.utilities.EntityManagerUtility;
import trainingweb.entities.Member;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;


public class MemberDAO {

    public List<Member> getMembers()
    {

        Query query = EntityManagerUtility.em.createQuery("Select e from Member e",Member.class);
        return query.getResultList();
    }

    public boolean addMember(Member member)
    {

        System.out.println("object "+member.getMemberId());
        EntityManagerUtility.em.getTransaction().begin();
        EntityManagerUtility.em.persist(member);
        EntityManagerUtility.em.getTransaction().commit();

        return true;
    }

}
