package com.sbnz.bankcredit.config;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.sbnz.bankcredit.security.RestAuthenticationEntryPoint;
import com.sbnz.bankcredit.security.TokenAuthenticationFilter;
import com.sbnz.bankcredit.security.TokenUtils;
import com.sbnz.bankcredit.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	private static final String RULES_PATH = "rules/";
//	@Bean
//	public KieFileSystem kieFileSystem() throws IOException {
//		KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
//		for (Resource file : getRuleFiles()) {
//			System.out.println(file.getFilename());
//			kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
//		}
//		return kieFileSystem;
//	}
//	private Resource[] getRuleFiles() throws IOException {
//		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//		return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
//	}
//	@Bean
//	public KieContainer kieContainer() throws IOException {
//		final KieRepository kieRepository = getKieServices().getRepository();
//		kieRepository.addKieModule(new KieModule() {
//			public ReleaseId getReleaseId() {
//				return kieRepository.getDefaultReleaseId();
//			}
//		});
//		KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
//		kieBuilder.buildAll();
//		return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
//	}
//	private KieServices getKieServices() {
//		return KieServices.Factory.get();
//	}
//	
	@Bean
	public KieContainer kieContainer() {
		 KieServices ks = KieServices.Factory.get();
         KieContainer kContainer = ks.getKieClasspathContainer();
         return kContainer;
         //		return KieServices.Factory.get().getKieClasspathContainer();
	}
	   
	// Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
	// BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	// Neautorizovani pristup zastcenim resursima
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// Definisemo nacin autentifikacije
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Autowired
	TokenUtils tokenUtils;

	@Bean
    public CorsFilter corsFilter() {
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(1800L);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
	// Definisemo prava pristupa odredjenim URL-ovima
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			// komunikacija izmedju klijenta i servera je stateless
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.cors().and()
			// za neautorizovane zahteve posalji 401 gresku
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			
			// svim korisnicima dopusti da pristupe putanjama /auth/** i /h2-console/**
			.authorizeRequests()
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/**").permitAll()
			
			// svaki zahtev mora biti autorizovan
			.anyRequest().authenticated().and()
			
			// presretni svaki zahtev filterom
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

		http.csrf().disable();
	}

	// Generalna bezbednost aplikacije
	@Override
	public void configure(WebSecurity web)  {
		// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
		web.ignoring().antMatchers("/front/**");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
	}

}
