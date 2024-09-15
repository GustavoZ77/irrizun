package zuun.tech.budget.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String description;
    private Double salesPrice;
    private Double basePrice;
    @ManyToOne
    private Unity unity;
    @ManyToMany
    private Set<Provider> provider;

}
