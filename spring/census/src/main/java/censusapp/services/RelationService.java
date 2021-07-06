package censusapp.services;

import censusapp.entities.Relations;
import censusapp.entities.RelationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RelationService {

    @Autowired
    RelationsRepository relationsRepository;

    public List<Relations> getRelations(String headMemberId)
    {
       return relationsRepository.findAllByFirstMember(headMemberId);
    }
    public Relations addRelation(Relations relation)
    {
        System.out.println(relation.getId());
        if(relation.getId()==null)
        {
            relation.setId(String.valueOf((new Random()).nextLong()));
        }
        return relationsRepository.save(relation);
    }

}
