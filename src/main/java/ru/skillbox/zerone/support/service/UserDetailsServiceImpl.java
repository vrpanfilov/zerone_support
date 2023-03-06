package ru.skillbox.zerone.support.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skillbox.zerone.support.model.entity.Role;
import ru.skillbox.zerone.support.model.entity.User;
import ru.skillbox.zerone.support.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findUserByEmail(username)
        .orElseThrow(() ->
            new UsernameNotFoundException(String.format("Username %s not found", username))
        );
    if (!user.isEnabled()) {
      throw (new UsernameNotFoundException(String.format("Username %s not found", username)));
    }
    Optional<Role> optionalRole = user.getRoles().stream()
        .filter(role -> role.getAuthority().endsWith("ADMIN"))
        .findFirst();
    if (!optionalRole.isPresent())
      throw (new UsernameNotFoundException(String.format("Username %s not found", username)));
    return user;
  }
}
