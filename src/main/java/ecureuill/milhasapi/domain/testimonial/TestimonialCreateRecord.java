package ecureuill.milhasapi.domain.testimonial;

import jakarta.validation.constraints.NotBlank;

public record TestimonialCreateRecord(
    @NotBlank
    String name,
    String photo,
    @NotBlank
    String testimonial
) {
    
}
