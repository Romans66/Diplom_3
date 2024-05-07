package testsettings.utills.obj;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserObj {
    private String email;
    private String password;
    private String name;
    
}