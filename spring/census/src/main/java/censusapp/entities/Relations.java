package censusapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "relationship")
public class Relations {
    @Id
    @Column(name = "id")
    private String Id;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "first_member", referencedColumnName = "member_id")
    private Member firstMember;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "second_member", referencedColumnName = "member_id")
    private Member secondMember;

    @Column(name="relationship")
    private String relationship;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Member getFirstMember() {
        return firstMember;
    }

    public void setFirstMember(Member firstMember) {
        this.firstMember = firstMember;
    }

    public Member getSecondMember() {
        return secondMember;
    }

    public void setSecondMember(Member secondMember) {
        this.secondMember = secondMember;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
