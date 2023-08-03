package ecureuill.milhasapi.domain.destination;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DestinationCreateRecord(
    @NotBlank
    String name,
    @NotBlank
    String photo,
    @NotBlank
    String photo2,
    @NotBlank
    @Size(max = 160)
    String meta,
    String description,
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction=2)
    BigDecimal price
) {
    
}
