package zuun.tech.budget.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zuun.tech.budget.domain.User;
import zuun.tech.budget.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        User user = userOptional.get();

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))  // Convertir cada rol en un GrantedAuthority
                .collect(Collectors.toList());

        System.out.println(user.getUserName());
        System.out.println(user.getPwd());

        user.getRoles().stream().forEach(System.out::println);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPwd())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()))
                .build();
    }
}
