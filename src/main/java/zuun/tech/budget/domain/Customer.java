package zuun.tech.budget.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String RFC;
    private String email;
    private Boolean status;
    @OneToMany
    private Set<Quotation> quotations;

}
