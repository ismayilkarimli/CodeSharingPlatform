package platform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "code with this UUID does not exist")
public class CodeNotFoundException extends RuntimeException {

    public CodeNotFoundException(String message) {
        super(message);
    }
}
