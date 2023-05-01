package com.example.photoExchange.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.photoExchange.configs.jwt.JwtUtils;
import com.example.photoExchange.models.ERole;
import com.example.photoExchange.models.Role;
import com.example.photoExchange.models.User;
import com.example.photoExchange.dto.SignUserOutDto;
import com.example.photoExchange.dto.ResponseDto;
import com.example.photoExchange.dto.SignUserDtoIn;
import com.example.photoExchange.repository.RoleRepository;
import com.example.photoExchange.repository.UserRepository;
import com.example.photoExchange.service.UserDetailsImpl;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SecurityController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRespository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authUser(@RequestBody SignUserDtoIn signUserDtoIn) {
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						signUserDtoIn.getUsername(),
						signUserDtoIn.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new SignUserOutDto(
				userDetails.getId(), 
				userDetails.getUsername(),
				jwt,
				roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUserDtoIn signUserDtoIn) {

		if (userRespository.existsByUsername(signUserDtoIn.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new ResponseDto("Error: Username is exist"));
		}

		if (!signUserDtoIn.getUsername().matches("[a-z0-9_\\-.@]{4,32}")) {
			throw new ValidationException("Username can contain only a-z 0-9 @ . -");
		}

		if (!signUserDtoIn.getPassword().matches("[a-zA-Z0-9]{8,500}")) {
			throw new ValidationException("Password can contain only a-z A-Z 0-9");
		}

		User user = new User(signUserDtoIn.getUsername(),
				passwordEncoder.encode(signUserDtoIn.getPassword()));

		Set<String> reqRoles = signUserDtoIn.getRoles();
		Set<Role> roles = new HashSet<>();

		if (reqRoles == null) {
			Role userRole = roleRepository
					.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
			roles.add(userRole);
		} else {
			reqRoles.forEach(r -> {
				switch (r) {
					case "admin":
						Role adminRole = roleRepository
								.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
						roles.add(adminRole);

						break;

					default:
						Role userRole = roleRepository
								.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
						roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRespository.save(user);
		return ResponseEntity.ok(new ResponseDto("User CREATED"));
	}
}

