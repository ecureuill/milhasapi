package ecureuill.milhasapi.domain.destination;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public record DestinationCreateRecord(
    @NotBlank
    String name,
    @NotBlank
    String photo,
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction=2)
    BigDecimal price
) {
    
}
