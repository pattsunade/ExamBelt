package com.patriciadelgado.consumidores.Services;

import java.util.List;
import java.util.Optional;

import com.patriciadelgado.consumidores.Models.User;
import com.patriciadelgado.consumidores.Repositories.UserRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public User findUserById(Long id) {
    	Optional<User> user = userRepository.findById(id);
    	
    	if(user.isPresent()) {
            return user.get();
    	} else {
    	    return null;
    	}
    }
    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return false;
        } else {

            if (BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public List<User> allUser() {
        return userRepository.findAll();
    }

    public List<User> findBySubs() {
        return userRepository.findBySubssIdIsNotNull();
    }

    public boolean emailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public long count() {
        return userRepository.count();
    }

    public User guardar(User user) {
        return userRepository.save(user);
    }
 }
