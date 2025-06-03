package org.tukorea.free.service;

import org.tukorea.free.domain.UserDTO;

public interface UserService {
    boolean existsById(String id); // id 중복 여부 확인
    void saveUser(UserDTO userDTO); // id 중복 시 저장하지 않게 처리
    UserDTO getUserById(String id);
}
