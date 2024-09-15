package zuun.tech.budget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuun.tech.budget.domain.Unity;
import zuun.tech.budget.repository.UnityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UnityService {

    @Autowired
    private UnityRepository unityRepository;

    public Unity save(Unity unity) {
        return unityRepository.save(unity);
    }

    public List<Unity> findAll() {
        return (List<Unity>) unityRepository.findAll();
    }

    public Unity findById(Integer id) {
        Optional<Unity> result = unityRepository.findById(id);
        return result.orElse(null);
    }

    public void deleteById(Integer id) {
        unityRepository.deleteById(id);
    }
}
