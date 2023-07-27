package ecureuill.milhasapi.domain.testimonial;

import jakarta.validation.constraints.NotBlank;

public record TestimonialUpdateRecord(
    @NotBlank
    String name,
    @NotBlank
    String testimonial,
    String photo
) {
    
}
