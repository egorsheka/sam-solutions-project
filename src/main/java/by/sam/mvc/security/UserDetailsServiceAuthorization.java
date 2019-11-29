package by.sam.mvc.security;

//import com.samsolutions.rentmedear.common.model.user.Role;
//import com.samsolutions.rentmedear.common.model.user.User;
//import com.samsolutions.rentmedear.common.model.user.UserRole;
import by.sam.mvc.models.user.Cook;
import by.sam.mvc.models.user.UserEntity;
import by.sam.mvc.service.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceAuthorization implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceAuthorization(UserService userService) {
        this.userService = userService;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        UserEntity user = userService.read(mail);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }

}


