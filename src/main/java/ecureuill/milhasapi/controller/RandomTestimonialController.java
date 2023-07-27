package ecureuill.milhasapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecureuill.milhasapi.domain.testimonial.TestimonialDetailRecord;
import ecureuill.milhasapi.domain.testimonial.TestimonialRepository;

@RestController
@RequestMapping("/depoimentos-home")
public class RandomTestimonialController {
    
    @Autowired
    private TestimonialRepository repository;

    @GetMapping
    public ResponseEntity<List<TestimonialDetailRecord>> get() {
        var testimonials = repository.findThreeTestimonials();
        return ResponseEntity.ok().body(testimonials.stream().map(TestimonialDetailRecord::new).collect(Collectors.toList()));
    }
}
