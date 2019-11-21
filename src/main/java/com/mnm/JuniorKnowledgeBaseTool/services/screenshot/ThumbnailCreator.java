package com.mnm.JuniorKnowledgeBaseTool.services.screenshot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.io.FileUtils;

import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class ScreeenMain {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String customerKey = "b4b44d";
        String secretPhrase = "";//leave secret phrase empty, if not needed

        ScreenshotMachine sm = new ScreenshotMachine(customerKey, secretPhrase);

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dkba0jg6y",
                "api_key", "477891882997543",
                "api_secret", "XWRfmWrIsCv5sKa3886e5dcPiUc"));


        Map options = new HashMap();
// mandatory parameter
        options.put("url", "https://developer.android.com");
// all next parameters are optional, see our website screenshot API guide for more details
        options.put("dimension", "1366x768"); // or "1366xfull" for full length screenshot
        options.put("device", "desktop");
        options.put("format", "png");
        options.put("cacheLimit", "0");
        options.put("delay", "200");
        options.put("zoom", "100");

        String apiUrl = sm.generateScreenshotApiUrl(options);
// put link to your html code
        System.out.println(apiUrl);

// or save screenshot as an image
        URLConnection openConnection = new URL(apiUrl).openConnection();
        openConnection.addRequestProperty("User-Agent", "Mozilla/4.0");
        InputStream in = openConnection.getInputStream();


        String output = "out.png";
        File file=new File(output);
        FileUtils.copyInputStreamToFile(in, file);

        cloudinary.uploader().upload(file, ObjectUtils.asMap(

                "resource_type","auto"
        ));
//        Files.copy(in, Paths.get(output), StandardCopyOption.REPLACE_EXISTING);
//        System.out.println("Screenshot saved as " + output);
    }
}
//b4b44d

//    public static File stream2file (InputStream in) throws IOException {
//        final File tempFile = File.createTempFile("flename", ".tmp");
//        tempFile.deleteOnExit();
//        try (FileOutputStream out = new FileOutputStream(tempFile)) {
//            IOUtils.copy(in, out);
//        }
//        return tempFile;
//    }