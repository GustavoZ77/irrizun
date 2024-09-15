package zuun.tech.budget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuun.tech.budget.domain.Provider;
import zuun.tech.budget.repository.ProviderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    public List<Provider> findAll() {
        return (List<Provider>) providerRepository.findAll();
    }

    public Provider findById(Long id) {
        Optional<Provider> result = providerRepository.findById(id);
        return result.orElse(null);
    }

    public void deleteById(Long id) {
        providerRepository.deleteById(id);
    }
}
