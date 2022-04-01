package si.codemonkee.relationsWithJavers.controller;

import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.codemonkee.relationsWithJavers.model.CommonPart;
import si.codemonkee.relationsWithJavers.repository.CommonPartRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/common-parts")
public class CommonPartController {

    @Autowired
    CommonPartRepository commonPartRepository;

    @Autowired
    Javers javers;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommonPart>> findAll() {
        List<CommonPart> commonParts = commonPartRepository.findAll();

        return new ResponseEntity<>(commonParts, HttpStatus.OK);
    }

    @GetMapping(value = "/audit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Changes> findAllAudit() {
        Changes changes = javers.findChanges(QueryBuilder.byClass(CommonPart.class).withNewObjectChanges().build());
        changes.forEach(change -> System.out.println("- " + change));


        return new ResponseEntity<>(changes, HttpStatus.OK);
    }

    @GetMapping("/audit/{auditId}")
    public String getProductChanges(@PathVariable long auditId) {
        CommonPart commonPart = commonPartRepository.findById(auditId).get();
        QueryBuilder jqlQuery = QueryBuilder.byInstance(commonPart);
        Changes changes = javers.findChanges(jqlQuery.build());
        return javers.getJsonConverter().toJson(changes);
    }

    @RequestMapping(value = "/{partId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonPart> findOne(@PathVariable String partId) {
        CommonPart commonParts = commonPartRepository.findById(Long.valueOf(partId)).get();

        return new ResponseEntity<>(commonParts, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonPart> create(@Valid @RequestBody CommonPart dto) {
        CommonPart commonPart = commonPartRepository.save(dto);

        return new ResponseEntity<>(commonPart, HttpStatus.OK);
    }

    @RequestMapping(value = "/{commonPartId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonPart> update(@PathVariable String commonPartId, @Valid @RequestBody CommonPart dto) {
        CommonPart commonPart = commonPartRepository.findById(Long.valueOf(commonPartId)).get();
        dto.setId(commonPart.getId());

        return new ResponseEntity<>(commonPartRepository.save(dto), HttpStatus.OK);
    }
}
