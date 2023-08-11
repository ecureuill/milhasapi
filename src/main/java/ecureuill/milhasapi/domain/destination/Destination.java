package ecureuill.milhasapi.domain.destination;

import java.math.BigDecimal;

import ecureuill.milhasapi.infra.openai.GptGuideService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "destinations")
@Entity(name = "Destination")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photo;    
    private String photo2;
    private String meta;
    private String description;
    private BigDecimal Price;
    
    public Destination(@Valid DestinationCreateRecord record) {
        this.name = record.name();
        this.photo = record.photo();
        this.photo2 = record.photo2();
        this.Price = record.price(); 
        this.meta = record.meta();
        this.description = record.description();  
        
        if(description == null || description == ""){
            GptGuideService gpt = new GptGuideService();
            this.description = gpt.generate(this.name);
        }
    }

    public void update(@Valid DestinationUpdateRecord record) {
        this.name = record.name();
        this.photo = record.photo();
        this.photo2 = record.photo2();
        this.meta = record.meta();
        this.description = record.description();
        this.Price = record.price();
    }
}
