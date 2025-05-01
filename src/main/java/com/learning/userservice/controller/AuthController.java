package com.learning.userservice.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.userservice.entities.ERole;
import com.learning.userservice.entities.JwtResponse;
import com.learning.userservice.entities.LoginRequest;
import com.learning.userservice.entities.LogoutRequest;
import com.learning.userservice.entities.MessageResponse;
import com.learning.userservice.entities.RefreshToken;
import com.learning.userservice.entities.RefreshTokenRequest;
import com.learning.userservice.entities.Role;
import com.learning.userservice.entities.SignupRequest;
import com.learning.userservice.entities.User;
import com.learning.userservice.jwt.JwtUtils;
import com.learning.userservice.repositories.RoleRepository;
import com.learning.userservice.repositories.UserRepository;
import com.learning.userservice.service.RefereshTokenService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://localhost:8081", "http://192.168.1.6:8081"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {@Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepository userRepository;

	  @Autowired
	  RoleRepository roleRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	  
	  @Autowired
	  private RefereshTokenService refreshTokenService;
	  
	  @Autowired
	  private UserDetailsService userDetailsService;
	  
	  @PostMapping("/login")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		  
		  this.doAuthenticate(loginRequest.getUsername(), loginRequest.getPassword());
		  UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
		  String token = this.jwtUtils.generateJwtToken(userDetails);
			//refresh token logic
		    RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginRequest.getUsername());
			JwtResponse response = JwtResponse.builder().token(token).refreshToken(refreshToken.getRefreshToken()).username(userDetails.getUsername()).build();
			
			return new ResponseEntity<>(response, HttpStatus.OK);


//	    Authentication authentication = authenticationManager.authenticate(
//	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//	    SecurityContextHolder.getContext().setAuthentication(authentication);
//	    
//	    String jwt = jwtUtils.generateJwtToken(authentication);
//	    //refresh token logic
//	    RefreshToken refreshToken = refereshTokenService.createRefreshToken(loginRequest.getUsername());
//	    
//	    JwtResponse response = JwtResponse.builder()
//	    		.token(jwt)
//	    		.refreshToken(refreshToken.getRefreshToken())
//	    		.username(loginRequest.getUsername()).build();
//	    		
//	    
//	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
//	    List<String> roles = userDetails.getAuthorities().stream()
//	        .map(item -> item.getAuthority())
//	        .collect(Collectors.toList());
//
////	    return ResponseEntity.ok(new JwtResponse(jwt, 
////	                         userDetails.getId(), 
////	                         userDetails.getUsername(), 
////	                         userDetails.getEmail(), 
////	                         roles));
//	    
//	    return new ResponseEntity<>(response, HttpStatus.OK);
	  }

	  private void doAuthenticate(String username, String password) {
		  UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
			try {
				authenticationManager.authenticate(authentication);
			} catch (BadCredentialsException e) {
				throw new RuntimeException("Invalide username or password");
			}
	}

	@PostMapping("/register")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email is already in use!"));
	    }

	    // Create new user's account
	    User user = new User(signUpRequest.getUsername(), 
	               signUpRequest.getEmail(),
	               encoder.encode(signUpRequest.getPassword()), signUpRequest.getRole());

	    Set<String> strRoles = signUpRequest.getRole();
	    Set<Role> roles = new HashSet<>();

	    if (strRoles == null) {
	      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "admin":
	          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(adminRole);

	          break;
	        case "mod":
	          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(modRole);

	          break;
	        default:
	          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(userRole);
	        }
	      });
	    }

	    user.setRoles(roles);
	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }
	  
	  @PostMapping("/refresh-token")
	  public JwtResponse refreshJwtToken(@RequestBody RefreshTokenRequest request){
		RefreshToken refreshToken =  refreshTokenService.verifyRefreshToken(request.getRefreshToken());
		User user =  refreshToken.getUser();
		UserDetails userDetails = (UserDetails) user;
		    String token = jwtUtils.generateJwtToken(userDetails);		
		    System.out.println("hello "+token);
		return JwtResponse.builder().refreshToken(refreshToken.getRefreshToken())
				.token(token)
				.username(user.getUsername()).build();
	  }
	  
	  @PostMapping("/logout")
	  public ResponseEntity<?> logoutUser(@Valid @RequestBody LogoutRequest logoutRequest) {
	      String refreshToken = logoutRequest.getRefreshToken();

	      Optional<RefreshToken> tokenOpt = refreshTokenService.findByToken(refreshToken);
	      if (tokenOpt.isPresent()) {
	    	  refreshTokenService.logoutUser(tokenOpt.get().getUser());
	          return ResponseEntity.ok("Logout successful, refresh token revoked.");
	      } else {
	          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Refresh token not found.");
	      }
	  }
	  
}