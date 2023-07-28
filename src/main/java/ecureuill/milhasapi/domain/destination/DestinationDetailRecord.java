package ecureuill.milhasapi.domain.destination;

import java.math.BigDecimal;

public record DestinationDetailRecord(
    Long id,
    String name,
    String photo,
    BigDecimal price
) {
    public DestinationDetailRecord(Destination destination) {
        this(
            destination.getId(),
            destination.getName(),
            destination.getPhoto(),
            destination.getPrice()
        );
    }
}
