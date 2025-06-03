package org.tukorea.free.service;

import org.tukorea.free.domain.UserDTO;

public interface UserService {
	// 주문자 중복 체크
	public boolean existsById(String id); // id 중복 여부 확인
    
    // 주문자 저장
	public void saveUser(UserDTO userDTO); // id 중복 시 저장하지 않게 처리
    
    // 주문자 불러오기
	public UserDTO getUserById(String id);
}
