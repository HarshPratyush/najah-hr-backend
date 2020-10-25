package in.co.najah.najahhr.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {



    public static String getMimeType(String base64File){
        return "image/png";
//        final Pattern mime = Pattern.compile("^data:([a-zA-Z0-9]+/[a-zA-Z0-9]+).*,.*");
//        final Matcher matcher = mime.matcher(base64File);
//        return matcher.group(1).toLowerCase();
    }

    public  static byte[] writeBase64ToFile(String base64File,String fileName,String fileExt) throws IOException {
        return Base64.getDecoder()
                .decode(base64File.split(",")[1].getBytes(StandardCharsets.UTF_8));
    }
}
