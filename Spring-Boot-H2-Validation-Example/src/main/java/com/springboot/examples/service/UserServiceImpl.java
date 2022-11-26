package com.springboot.examples.service;

import com.springboot.examples.domain.User;
import com.springboot.examples.exception.UserNotFoundException;
import com.springboot.examples.model.AddressInfo;
import com.springboot.examples.model.UserInfo;
import com.springboot.examples.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public int createUser(UserInfo userInfo) {
        User user = modelMapper.map(userInfo, User.class);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public UserInfo fetchUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        UserInfo userInfo =  modelMapper.map(user, UserInfo.class);
        userInfo.setAddressInfo(modelMapper.map(user.getAddress(), AddressInfo.class));
        return userInfo;
    }
}
