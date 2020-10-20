//package cz.fim.uhk.insertions.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//class DatabaseUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;    // třída, která z databáze vytáhne uživatele
//    private final UserDetailsMapper userDetailsMapper;
//
//    // constructor ...
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException {
//        UserCredentials userCredentials =
//                userRepository.findByUsername(username);
//        return userDetailsMapper.toUserDetails(userCredentials);
//    }
//}
