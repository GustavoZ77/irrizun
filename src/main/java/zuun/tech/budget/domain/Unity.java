package zuun.tech.budget.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Unity {

    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    private Boolean status;

}
