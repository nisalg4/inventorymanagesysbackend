package com.example.springsecuritymongo.response;
import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import org.springframework.data.annotation.Id;
        import org.springframework.data.mongodb.core.mapping.DBRef;
        import org.springframework.data.mongodb.core.mapping.Document;

        import java.util.HashSet;
        import java.util.Set;

@Document(collection = "User")
@Getter
@Setter
@NoArgsConstructor
public class userResponse {
    @Id
    private String id;
    private String username;
    private String password;


    public userResponse(String username, String email, String password) {
        this.username = username;
        this.password = password;
    }
}