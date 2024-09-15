package zuun.tech.budget.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class QuotationDetail {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Quotation quotation;
    @ManyToOne
    public Product product;
    private int quantity;

}
