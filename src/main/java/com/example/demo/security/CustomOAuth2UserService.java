package com.example.demo.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserJpaRepository;
import com.example.demo.vo.UsersVO;

import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private KakaoLoginService kus = new KakaoLoginService();
	
	private final UserJpaRepository ur;
	private final HttpSession session;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("loadUser 실행");
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();

		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
				.getUserNameAttributeName();

		OAuth2Attributes attributes = OAuth2Attributes.of(registrationId, userNameAttributeName,
				oAuth2User.getAttributes());
		
		System.out.println("test !! attributes : ");
		System.out.println(attributes);

		UsersVO user = loadOrSave(attributes);
		System.out.println("test !! user : ");
		System.out.println(user);
		session.setAttribute("user", new SessionUser(user));
		return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
				attributes.getAttributes(), attributes.getNameAttributeKey());
	}
	// 이미 저장된 유저라면 load, 아니면 save
	private UsersVO loadOrSave(OAuth2Attributes attributes) {
		System.out.println("loadOrSave 실행");
		System.out.println("attributes :");
		System.out.println(attributes);
		// 이미 저장된 유저인지 확인
		Optional<UsersVO> usercheck = ur.findByEmail(attributes.getEmail());
		if (usercheck.isEmpty()) { // 소셜로그인을 시도한 아이디가 없다면 저장하고

			UsersVO newUser = new UsersVO();
			newUser.setEmail(attributes.getEmail());
			newUser.setId(attributes.getId());
			newUser.setU_name(attributes.getId());
			newUser.setPwd(new BCryptPasswordEncoder().encode(kus.makePwd()));
			newUser.setNickname(attributes.getId());

			newUser.setRole(Role.USER);
			System.out.println("new USer : "+newUser);
			ur.insert(newUser);

			return newUser;
			
		} else {

            return usercheck.get();
		}
	}
}

