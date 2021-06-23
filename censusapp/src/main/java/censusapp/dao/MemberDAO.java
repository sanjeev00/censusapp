package censusapp.dao;

import censusapp.CensusApplication;
import censusapp.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MemberDAO {

    public List<Member> getMembers()
    {
        try {
            Statement st = CensusApplication.con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM member");
            while (rs.next()) {

                System.out.println(rs.getString("first_name")+" "+
                rs.getString("gender")+rs.getDate("date_of_birth"));
               }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
        //return CensusApplication.session.createQuery("from Member").getResultList();
    }

    public boolean addMember(Member member)
    {
        CensusApplication.session.beginTransaction();
        CensusApplication.session.save(member);
        CensusApplication.session.getTransaction().commit();
        return true;
    }

}
