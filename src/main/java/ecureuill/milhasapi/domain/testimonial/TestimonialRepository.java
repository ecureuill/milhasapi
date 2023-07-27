package ecureuill.milhasapi.domain.testimonial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long>{

    @Query("""
        select t from Testimonial t
        order by rand()
        limit 3
    """)
    List<Testimonial> findThreeTestimonials();
    
}
