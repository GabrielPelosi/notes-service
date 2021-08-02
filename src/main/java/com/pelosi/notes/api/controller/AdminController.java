package com.pelosi.notes.api.controller;


import com.pelosi.notes.config.security.JwtUtil;
import com.pelosi.notes.exception.InvalidCredentialsException;
import com.pelosi.notes.model.AdminLoginRequest;
import com.pelosi.notes.model.AdminLoginResponse;
import com.pelosi.notes.service.NoteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final @NonNull NoteService noteService;

    private final @NonNull JwtUtil jwtUtil;

    private final @NonNull AuthenticationManager authenticationManager;

    @DeleteMapping(value = "/note/{id}")
    public ResponseEntity<?> deleteNoteAsAdmin(@PathVariable Long id){
        return noteService.deleteNoteById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(Boolean.FALSE));
    }


    @PostMapping(value = "/login")
    public ResponseEntity<AdminLoginResponse> adminLogin(@RequestBody AdminLoginRequest adminLoginRequest){
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(adminLoginRequest.getUsername(),
                            adminLoginRequest.getPassword()));
        } catch (Exception ex) {
            throw new InvalidCredentialsException("invalid username/password");
        }

        return ResponseEntity.ok(AdminLoginResponse.builder()
                .jwtToken(jwtUtil.generateToken(adminLoginRequest.getUsername()))
                .build());
    }
}
