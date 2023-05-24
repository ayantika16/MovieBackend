package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.SecretNameMismatchException;
import com.example.demo.exception.UserNotFoundException;
import com.example.entity.JwtRequest;
import com.example.entity.JwtResponse;
import com.example.entity.User;
import com.example.service.JwtService;
import com.example.service.UserService;
import com.example.util.JwtUtil;

@RestController
@RequestMapping("auth/v1")
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping({"/login"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
    
    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }
    
    @GetMapping("/forgot/{name}")
	public ResponseEntity<?> forgotPassword(@PathVariable String name) throws UserNotFoundException{
		
		String str=userService.forgotPassword(name);
		
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	
	@PutMapping("/forgot/{name}/updatePassword")
	public ResponseEntity<?> updatePassword(@PathVariable String name, @RequestBody User user) throws UserNotFoundException,SecretNameMismatchException{
		String petname=user.getPetname();
		String password=user.getPassword();
		
		User u=userService.updatePassword(name, petname, password);
		
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<Boolean> validateJwt(@RequestHeader("Authorization") String jwt) {
		//ResponseEntity<Boolean> response=null;
		
		jwt=jwt.substring(7);
		final UserDetails userDetails = jwtService.loadUserByUsername(jwtUtil.getUsernameFromToken(jwt));
		
		try {
			if(jwtUtil.validateToken(jwt, userDetails)) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
		//return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
//	@PostMapping({"/validate"})
//	 public Boolean validateJwt(@RequestHeader("Authorization") String jwt){
//
////	        ResponseEntity<Boolean> response = null;
//		boolean response=false;
//	        jwt = jwt.substring(7);
//	        final UserDetails userDetails = jwtService.loadUserByUsername(jwtUtil.getUsernameFromToken(jwt));
//	        try{
//	            if(jwtUtil.validateToken(jwt, userDetails)){
////	                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
//		response=true;
//	            }else{
////	                return new ResponseEntity<Boolean>(false, HttpStatus.OK); 
//		response=false;
//	            }
//	        }catch(Exception e) {
////	            response = new ResponseEntity<Boolean>(false, HttpStatus.OK); 
//		response=false;
//	        }
//	      
//	        return response;
//	    }
//	
	
}
