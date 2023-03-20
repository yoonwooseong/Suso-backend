package suso.backend.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import suso.backend.domain.user.User;
import suso.backend.domain.user.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username " + username);
        User user = userRepository.findByAccount(username).orElseThrow(
                () -> new UsernameNotFoundException("Invalid authentication !")
        );

        return new CustomUserDetails(user);
    }
}
