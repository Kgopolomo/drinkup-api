package za.co.drinkup.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.drinkup.api.entity.UserProfile;
import za.co.drinkup.api.repository.UserProfileRepository;
import za.co.drinkup.api.request.LoginRequest;
import za.co.drinkup.api.response.JwtAuthenticationResponse;
import za.co.drinkup.api.util.JWTUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService implements UserDetailsService {
    @Autowired private UserProfileRepository userProfileRepository;

    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    public UserProfile updateProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public UserProfile getProfile(Long userId) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
        return userProfile.orElse(null);
    }

    public List<UserProfile> getAllProfiles() {
        return userProfileRepository.findAll();
    }

    public void deleteProfile(Long userId) {
        userProfileRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        if (userProfile == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(userProfile.getUsername(), userProfile.getPassword(),
                new ArrayList<>());
    }

    public UserProfile createProfile(UserProfile userProfile) {
        userProfile.setPassword(bCryptPasswordEncoder.encode(userProfile.getPassword()));
        return userProfileRepository.save(userProfile);
    }


    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        JwtAuthenticationResponse token = new JwtAuthenticationResponse(jwt, "Bearer", userDetails);
        return token;
    }

    public UserProfile getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserProfile profile = userProfileRepository.findByUsername(userDetails.getUsername());
        return profile;
    }
}
