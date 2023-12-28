package com.foodservices.foodservicesrecipes.useCase;

import com.foodservices.foodservicesrecipes.dto.UserDTO;
import com.foodservices.foodservicesrecipes.entity.User;
import com.foodservices.foodservicesrecipes.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase extends PersistenceBasedUseCase {
    private UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO execute(UserDTO userDTO){
        User user = userDTO.generateEntity();
        User createdUserEntity = this.userRepository.save(user);
        return new UserDTO(createdUserEntity);
    }
}
