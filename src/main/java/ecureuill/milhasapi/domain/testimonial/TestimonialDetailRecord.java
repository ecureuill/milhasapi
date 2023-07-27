package ecureuill.milhasapi.domain.testimonial;

public record TestimonialDetailRecord(
    String name,
    String photo,
    String testimonial
) {

    public TestimonialDetailRecord(Testimonial doctor) {
        this(doctor.getName(), doctor.getPhoto(), doctor.getTestimonial());
    }
}
