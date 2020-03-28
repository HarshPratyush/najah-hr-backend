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

    private final static Path rootPath = Paths.get(Constants.ROOT_DIRC);


    public static String getMimeType(String base64File){
        return "image/png";
//        final Pattern mime = Pattern.compile("^data:([a-zA-Z0-9]+/[a-zA-Z0-9]+).*,.*");
//        final Matcher matcher = mime.matcher(base64File);
//        return matcher.group(1).toLowerCase();
    }

    public  static String writeBase64ToFile(String base64File,String fileName,String fileExt) throws IOException {
        byte[] decodedImg = Base64.getDecoder()
                .decode(base64File.split(",")[1].getBytes(StandardCharsets.UTF_8));
        InputStream is = null;
        is = new ByteArrayInputStream(decodedImg);


        String filename = fileName+new Date().getTime()+"."+fileExt;

        Files.copy(is,rootPath.resolve(filename).toAbsolutePath());

        return filename;
    }
}
