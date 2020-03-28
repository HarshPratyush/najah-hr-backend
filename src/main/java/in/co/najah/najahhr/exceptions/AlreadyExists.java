package in.co.najah.najahhr.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExists extends  Exception{

    public AlreadyExists(String message){
        super(message);
    }
}
