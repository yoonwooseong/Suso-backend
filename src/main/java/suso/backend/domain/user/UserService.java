package suso.backend.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suso.backend.api.security.JwtProvider;
import suso.backend.domain.user.dto.UserRequest;
import suso.backend.domain.user.dto.UserResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public UserResponse saveUser(UserRequest userRequest){
        return userRepository.save(createNewUser(userRequest)).toResponse();
    }

    public UserResponse login(UserRequest userRequest) throws Exception{
        User loginedUser = userRepository.findByAccount(userRequest.getAccount()).orElseThrow( ()->
                new BadCredentialsException("잘못된 계정 정보입니다.")
        );

        if(!passwordEncoder.matches(userRequest.getPassword(), loginedUser.getPassword())){
            throw new BadCredentialsException("잘못된 계정 정보입니다.");
        }

        return UserResponse.builder()
                .id(loginedUser.getId())
                .account(loginedUser.getAccount())
                .name(loginedUser.getName())
                .email(loginedUser.getEmail())
                .imageUrl(loginedUser.getImageUrl())
                .introduction(loginedUser.getIntroduction())
                .role(loginedUser.getRole())
                .token(jwtProvider.createToken(loginedUser.toResponse()))
                .build();
    }

    public UserResponse getUser(String account) throws Exception{
        User user = userRepository.findByAccount(account).orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));

        return user.toResponse();
    }

    private User createNewUser(UserRequest userRequest){
        return User.builder()
                .account(userRequest.getAccount())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .imageUrl(userRequest.getImageUrl())
                .introduction(userRequest.getIntroduction())
                .role(Role.USER)
                .build();
    }
}
