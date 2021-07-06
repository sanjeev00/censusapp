package censusapp.contoller;

import censusapp.entities.Relations;
import censusapp.services.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RelationController {

    @Autowired
    private RelationService relationService;

    @GetMapping("/relation/{headMemberId}")
    List<Relations> getRelations(@PathVariable String headMemberId )
    {
       return relationService.getRelations(headMemberId);
    }

    @PostMapping("/relation")
    List<Relations> addRelation(@RequestBody List<Relations> relations)
    {
        List<Relations> savedRelations = new ArrayList<>();
        for (Relations relation : relations) {
            savedRelations.add(relationService.addRelation(relation));
        }
        return savedRelations;
    }


}
