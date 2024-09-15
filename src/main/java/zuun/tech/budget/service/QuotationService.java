package zuun.tech.budget.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuun.tech.budget.domain.Product;
import zuun.tech.budget.domain.Quotation;
import zuun.tech.budget.domain.QuotationDetail;
import zuun.tech.budget.repository.ProductRepository;
import zuun.tech.budget.repository.QuotationRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuotationService {

    @Autowired
    private QuotationRepository quotationRepository;

    public List<Quotation> findAll() {
        return (List<Quotation>) quotationRepository.findAll();
    }

    public Optional<Quotation> findById(Long id) {
        return quotationRepository.findById(id);
    }


    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Quotation save(Quotation quotation) {
        return quotationRepository.save(quotation);
    }

    @Transactional
    public Quotation saveQuotation(Quotation quotation, Map<Long, Integer> productsWithQuantities) {
        // Set total amount
        double totalAmount = 0;

        // Clear previous details
        quotation.getQuotationDetails().clear();

        // Add new details
        for (Map.Entry<Long, Integer> entry : productsWithQuantities.entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            QuotationDetail detail = new QuotationDetail();
            detail.setProduct(product);
            detail.setQuantity(quantity);
            detail.setQuotation(quotation);

            totalAmount += product.getSalesPrice() * quantity;
            quotation.getQuotationDetails().add(detail);
        }

        // Update the amount
        quotation.setAmount(totalAmount);

        return quotationRepository.save(quotation);
    }

    public void deleteById (Long id){
        quotationRepository.deleteById(id);
    }
}