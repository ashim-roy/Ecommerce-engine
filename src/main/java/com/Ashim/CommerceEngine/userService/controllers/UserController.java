package com.Ashim.CommerceEngine.userService.controllers;



import com.Ashim.CommerceEngine.userService.dtos.LoginRequestDto;
import com.Ashim.CommerceEngine.userService.dtos.LogoutRequestDto;
import com.Ashim.CommerceEngine.userService.dtos.SignUpRequestDto;
import com.Ashim.CommerceEngine.userService.dtos.UserDto;
import com.Ashim.CommerceEngine.userService.models.Token;
import com.Ashim.CommerceEngine.userService.models.User;
import com.Ashim.CommerceEngine.userService.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//http://localhost:8080/users/
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignUpRequestDto requestDto) {;
        User user = userService.signUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );
        // convert this userobject into userDto  object
        return UserDto.from(user);  // Reads get the userDtop from User
    }

    //http://localhost:8080/users/login
    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto) {
        return null;

    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto) {
        return null;
    }

    @GetMapping("/validate")
    public UserDto validateToken(@PathVariable String tokenValue){
        return null;
    }

}
