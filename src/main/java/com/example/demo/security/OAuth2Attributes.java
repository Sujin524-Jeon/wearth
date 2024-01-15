package com.example.demo.security;

import java.util.Map;

import com.example.demo.vo.UsersVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class OAuth2Attributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String email;
	private String id;

	public OAuth2Attributes(Map<String, Object> attributes, String nameAttributeKey, String email,
			String id) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.email = email;
		this.id = id;
	}

	public OAuth2Attributes() {
	}

	private static OAuth2Attributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> response = (Map<String, Object>) attributes.get("response"); // 네이버에서 받은 데이터에서 프로필 정보다 담긴
																							// response 값을 꺼낸다.

		return new OAuth2Attributes(attributes, userNameAttributeName, 
				(String) response.get("email"), (String) response.get("profile_image"));
	}

	private static OAuth2Attributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {

		return new OAuth2Attributes(attributes, userNameAttributeName,
				(String) attributes.get("email"), (String) attributes.get("picture"));
	}

	// .. getter/setter 생략

	public UsersVO toEntity() {
		return UsersVO.builder()
				.id(id)
				.pwd(email)
				.email(email)
				.role(Role.USER)
				.build();
	}

	@SneakyThrows
	public static OAuth2Attributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		log.info("userNameAttributeName = {}",
				new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userNameAttributeName));
		log.info("attributes = {}", new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(attributes));
		String registrationIdToLower = registrationId.toLowerCase();
		switch (registrationIdToLower) {
		case "naver":
			return ofNaver(userNameAttributeName, attributes);
		case "kakao":
			return ofKakao(userNameAttributeName, attributes);
		case "google":
			return ofGoogle(userNameAttributeName, attributes);
		default:
			throw new Exception("해당 소셜 로그인은 현재 지원하지 않습니다.");
		}
	}

	private static OAuth2Attributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>) account.get("profile");
		System.out.println("ofkakao 실행!");
		System.out.println(attributes);
		System.out.println(account);
		System.out.println(profile);
		System.out.println(profile.get("nickname"));
		return OAuth2Attributes.builder()
				.email((String)account.get("email"))
				.id("kakao@"+attributes.get("id"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
			
	}

}

