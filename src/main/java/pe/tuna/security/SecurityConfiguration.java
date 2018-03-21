package pe.tuna.security;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // con este estereotipo hacemos la autenticacion por anotacion
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Miguel").password("{noop}chinchay").roles("USER")
        .and().withUser("admin").password("{noop}admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Autorizacion basica HTTTP
        // http.authorizeRequests().anyRequest().authenticated().and().httpBasic();

        // Autenticacion por URL y usuarios
        // Ademas desabilitamos el mecanismo csrf para no tener problemas al momento de realizar peticiones

        /*http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/apiSample/hello").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/apiSample/admin/hello").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/apiSample/admin/hello").hasRole("ADMIN")
                .and().httpBasic();
        */
        // Autenticacion mediante el uso de anotaciones en los metodos del controlador
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }


}
