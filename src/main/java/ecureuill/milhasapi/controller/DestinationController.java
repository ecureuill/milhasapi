package ecureuill.milhasapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import ecureuill.milhasapi.domain.destination.DestinationRepository;
import ecureuill.milhasapi.domain.destination.DestinationUpdateRecord;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import ecureuill.milhasapi.domain.destination.Destination;
import ecureuill.milhasapi.domain.destination.DestinationCreateRecord;
import ecureuill.milhasapi.domain.destination.DestinationDetailRecord;

@RestController
@RequestMapping("/destinos")
public class DestinationController {
    
    @Autowired
    private DestinationRepository repository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DestinationDetailRecord> create(@Valid @RequestBody DestinationCreateRecord record, UriComponentsBuilder uriBuilder) {

        var destination = repository.save(new Destination(record));

        var uri = uriBuilder.path("destinos/{id}").buildAndExpand(destination.getId()).toUri();

        return ResponseEntity.created(uri).body(new DestinationDetailRecord(destination));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DestinationDetailRecord>> getAll(@RequestParam(name="name", required = false) String name) {

        if(name != null){
            var result = repository.findAllByNameStartingWith(name);
            if (result.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum destino foi encontrado", null);
            }

            return ResponseEntity.ok().body(result.stream().map(DestinationDetailRecord::new).collect(Collectors.toList()));
        }


        return ResponseEntity.ok().body(repository.findAll().stream().map(DestinationDetailRecord::new).collect(Collectors.toList()));
    }

    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DestinationDetailRecord> getOne(@PathVariable Long id) {
        var data = repository.getReferenceById(id);

        return ResponseEntity.ok().body(new DestinationDetailRecord(data));
    }    
    

    @PutMapping(value = "/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DestinationDetailRecord> update(@PathVariable Long id, @Valid @RequestBody DestinationUpdateRecord record) {
        var destination = repository.getReferenceById(id);

        destination.update(record);

        return ResponseEntity.ok().body(new DestinationDetailRecord(destination));
    
    }

    @DeleteMapping(value = "{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
