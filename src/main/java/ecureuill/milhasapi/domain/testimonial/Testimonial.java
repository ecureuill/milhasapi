package ecureuill.milhasapi.domain.testimonial;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "testimonials")
@Entity(name = "Testimonial")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Testimonial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String testimonial;
    private String photo;
    
    public Testimonial(TestimonialCreateRecord record) {
        this.name = record.name();
        this.testimonial = record.testimonial();
        this.photo = record.photo();
    }

    public void update(TestimonialUpdateRecord record) {
        this.name = record.name();
        this.testimonial = record.testimonial();
        this.photo = record.photo();
    }
}
