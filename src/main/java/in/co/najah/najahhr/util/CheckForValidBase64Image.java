package in.co.najah.najahhr.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckForValidBase64Image implements ConstraintValidator<ValidImage,String> {
    @Override
    public void initialize(ValidImage constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

//        try {
//            final Pattern mime = Pattern.compile("^data:([a-zA-Z0-9]+/[a-zA-Z0-9]+).*,.*");
//            final Matcher matcher = mime.matcher(value);
//            return (matcher.group(1).toLowerCase().contains("image"));
//        }
//        catch (Exception e){
//            return false;
//        }

        return true;



    }
}
