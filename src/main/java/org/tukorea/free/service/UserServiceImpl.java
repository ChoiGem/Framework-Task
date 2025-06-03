package org.tukorea.free.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tukorea.free.domain.UserDTO;
import org.tukorea.free.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository userRepository;
	
	// 주문자 중복 체크
	public boolean existsById(String id) {
		return userRepository.existsById(id);
	}
    
    // 주문자 저장
	public void saveUser(UserDTO userDTO) {
		if (!userRepository.existsById(userDTO.getId())) {
            userRepository.save(UserDTO.toEntity(userDTO));
        }
	}
    
    // 주문자 불러오기
	public UserDTO getUserById(String id) {
		return userRepository.findById(id).map(UserDTO::toDTO).orElse(null);
	}
}
