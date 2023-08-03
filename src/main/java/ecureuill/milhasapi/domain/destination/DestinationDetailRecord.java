package ecureuill.milhasapi.domain.destination;

import java.math.BigDecimal;

public record DestinationDetailRecord(
    Long id,
    String name,
    String photo,
    String photo2,
    String meta,
    String description,
    BigDecimal price
) {
    public DestinationDetailRecord(Destination destination) {
        this(destination.getId(), destination.getName(), destination.getPhoto(), destination.getPhoto2(), destination.getMeta(), destination.getDescription(), destination.getPrice());
    }
}
