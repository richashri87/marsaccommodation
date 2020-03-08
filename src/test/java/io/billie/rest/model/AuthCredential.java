package io.billie.rest.model;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthCredential extends BaseModel{
	private String username;
	private String password;
}