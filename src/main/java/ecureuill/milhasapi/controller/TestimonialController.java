package ecureuill.milhasapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ecureuill.milhasapi.domain.testimonial.Testimonial;
import ecureuill.milhasapi.domain.testimonial.TestimonialCreateRecord;
import ecureuill.milhasapi.domain.testimonial.TestimonialDetailRecord;
import ecureuill.milhasapi.domain.testimonial.TestimonialRepository;
import ecureuill.milhasapi.domain.testimonial.TestimonialUpdateRecord;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/depoimentos")
public class TestimonialController {

    @Autowired
    private TestimonialRepository repository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TestimonialDetailRecord> save(@RequestBody @Valid TestimonialCreateRecord record, UriComponentsBuilder uriBuilder){
        var doctor = repository.save(new Testimonial(record));
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        var dto = new TestimonialDetailRecord(doctor);

        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<TestimonialDetailRecord>> getAll(){
        return ResponseEntity.ok().body(repository.findAll().stream().map(TestimonialDetailRecord::new).collect(Collectors.toList()));  
    }

    @PutMapping(value="/{id}")
    @Transactional
    public ResponseEntity<TestimonialDetailRecord> update(@PathVariable Long id, @RequestBody TestimonialUpdateRecord record) {
        var data = repository.findById(id);
        if (data.isEmpty()) {
            throw new EntityNotFoundException();
        }
        var testimonial = data.get();
        testimonial.update(record);

        return ResponseEntity.ok().body(new TestimonialDetailRecord(testimonial));
    }

    @DeleteMapping(value="/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var data = repository.findById(id);
        if (data.isEmpty()) {
            throw new EntityNotFoundException();
        }
        repository.deleteById(id);
        
        return ResponseEntity.noContent().build(); 
    }
    
}
