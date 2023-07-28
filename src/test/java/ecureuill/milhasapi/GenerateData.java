package ecureuill.milhasapi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ecureuill.milhasapi.domain.testimonial.Testimonial;
import net.datafaker.Faker;

public class GenerateData {
    private static Faker faker = new Faker();
    
    public static Testimonial randomTestimonial() {
        return new Testimonial(
            faker.random().nextLong(),
            faker.funnyName().name(),
            faker.lorem().sentence(),
            faker.internet().url()
        );
    }

    public static List<Testimonial> randomTestimonials(int count) {
        return IntStream.range(0, count)
          .mapToObj(i -> randomTestimonial())
          .collect(Collectors.toList());
    }
}
