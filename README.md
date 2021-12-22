# Spring boot authorization and authentication server
Configure to client websecurity for logout
```
@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().antMatcher("/**").authorizeRequests()
                .antMatchers("/css/**","/js/**","/img/**", "/vendor/**")
                .permitAll()
                .antMatchers("/").authenticated()
                .anyRequest()
                .authenticated()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //.addLogoutHandler(mySsoLogoutHandler)
                .invalidateHttpSession(true)
                .deleteCookies("KSESSION").logoutSuccessUrl("http://authserver-ip-address or (localhost):port/exit");
    }
```
    
Configure to resource server application.yml file. (It will check the token)
```
security:
  oauth2:
    resource:
      token-info-uri: http://authserver-ip-address or (localhost):port/oauth/check_token
    client:
      client-id: mobile
      client-secret: pin
```
