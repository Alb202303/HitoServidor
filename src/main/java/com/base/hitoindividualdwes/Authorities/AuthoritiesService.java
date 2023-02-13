package com.base.hitoindividualdwes.Authorities;

import com.base.hitoindividualdwes.model.Authorities;
import com.base.hitoindividualdwes.model.Comentario;
import com.base.hitoindividualdwes.model.Tema;
import com.base.hitoindividualdwes.model.Users;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Service
@ApplicationScope
public class AuthoritiesService {

    private AuthoritiesRepository authorities;

    public AuthoritiesService(AuthoritiesRepository authorities) {
        this.authorities = authorities;
    }

    public void borrarU(Users users) {
        List<Authorities> auth = authorities.borrarU(users);
        authorities.deleteAll(auth);
    }
}
