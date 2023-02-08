package za.co.drinkup.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.drinkup.api.entity.HappyHourDetails;
import za.co.drinkup.api.entity.UserProfile;
import za.co.drinkup.api.request.LoginRequest;
import za.co.drinkup.api.response.JwtAuthenticationResponse;
import za.co.drinkup.api.response.ResponseHandler;
import za.co.drinkup.api.service.UserProfileService;
import za.co.drinkup.api.util.JWTUtil;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Operation(summary = "New user", description = "", tags = {"auth"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserProfile.class)))})
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody UserProfile userProfile) {
        userProfileService.createProfile(userProfile);
        return ResponseHandler.responseBuilder("Signed up successful ", HttpStatus.CREATED, null);
    }

    @Operation(summary = "login user", description = "", tags = {"auth"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = LoginRequest.class)))})
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {

        ;


        return ResponseHandler.responseBuilder(null, HttpStatus.OK, userProfileService.login(loginRequest));
    }

}
