package ecureuill.milhasapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
    
}
