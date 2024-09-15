package zuun.tech.budget.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Quotation {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuotationDetail> quotationDetails;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Double amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryTime;
    private String description;
    private Integer validity;
    private Boolean status;
    @ManyToOne
    private Customer customer;

}
