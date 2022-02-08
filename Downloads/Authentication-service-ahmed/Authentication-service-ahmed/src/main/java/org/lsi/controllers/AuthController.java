package org.lsi.controllers;

import org.lsi.security.params;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import org.lsi.entities.ERole;
import org.lsi.entities.Role;
import org.lsi.entities.User;
import org.lsi.payload.request.LoginRequest;
import org.lsi.payload.request.SignupRequest;
import org.lsi.payload.request.AuthenticationRequest;
import org.lsi.payload.response.JwtResponse;
import org.lsi.payload.response.MessageResponse;
import org.lsi.repositories.RoleRepository;
import org.lsi.repositories.UserRepository;
import org.lsi.security.jwt.JwtUtils;
import org.lsi.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(),
												 userDetails.getPublicAddress(),
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 userDetails.getImage(),
												 userDetails.getDescription(),
												 userDetails.getPhone(),
												
												 roles));
	}

	
	
	
	
	
	@PostMapping("/signup")
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
							 //signUpRequest
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
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
	
	
	
	public User getUserById(String userId) {
		return userRepository.findById(userId).get();
		
	}
	
	@GetMapping("/getid")
	public String getUserId(HttpServletRequest request) {
		
	String jwt =request.getHeader("Authorization");
	String token=jwt.substring(7, jwt.length());
	String username=jwtUtils.getUserNameFromJwtToken(token);
	
		String id=userRepository.getByUsername(username).getId();
		
		return id;
	}
	
	//----user-------
	@GetMapping(value="/user")
    public User getUserFromRequest(HttpServletRequest request) {
	
		String jwt =request.getHeader("Authorization");
		String token=jwt.substring(7, jwt.length());
		String username=jwtUtils.getUserNameFromJwtToken(token);
		User user = userRepository.getByUsername(username);
        return user;
		
    }
	
	
	//----profile-------
  	@CrossOrigin(origins = "http://localhost:4200")
  	@PostMapping(value="/profile")
  	public String profile (@RequestBody AuthenticationRequest authenticationRequest,HttpServletRequest request ){
  		User user =getUserFromRequest(request);
  		
  		String image = authenticationRequest.getImage();
  		String description = authenticationRequest.getDescription();
  		long phone = authenticationRequest.getPhone();
  		
  		user.setImage(image);
  		user.setDescription(description);
  		user.setPhone(phone);
  		
  		try {
  			userRepository.save(user);
  			
  		} catch (Exception e) {

  			return "erreur";
  		}
  	
  		
  		
  		return "profile created";
  	}
  	
  	
  	
  	
  	@CrossOrigin(origins = "http://localhost:4200")
  	@GetMapping("/user/{id}")
  	public User getUserbyId(@PathVariable("id") String userId) {
  		
  		User userProfile=new User();
  		
  		Optional <User> user=userRepository.findById(userId);
		if(user.isEmpty()) {
			return null;
		};
		
		userProfile.setUsername(user.get().getUsername());
		userProfile.setDescription(user.get().getDescription());
		userProfile.setEmail(user.get().getEmail());
		userProfile.setId(user.get().getId());
		userProfile.setPhone(user.get().getPhone());
		userProfile.setImage(user.get().getImage());
		userProfile.setPublicAddress(user.get().getPublicAddress());
		
		return userProfile;
  	
  	}
  	
}