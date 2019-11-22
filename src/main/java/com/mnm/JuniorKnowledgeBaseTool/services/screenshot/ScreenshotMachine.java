package com.mnm.JuniorKnowledgeBaseTool.services.screenshot;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

@Service
public class ScreenshotMachine {

    private String customerKey;
    private String secretPhrase;
    private String apiBaseUrl = "https://api.screenshotmachine.com/?";
    private String pdfApiBaseUrl = "https://pdfapi.screenshotmachine.com/?";

    public ScreenshotMachine() {
    }

    public ScreenshotMachine(String customerKey, String secretPhrase) {
        this.customerKey = customerKey;
        this.secretPhrase = secretPhrase;
    }

    public String generateScreenshotApiUrl(Map<String, String> options) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return generateUrl(apiBaseUrl, options);
    }

    public String generatePdfApiUrl(Map<String, String> options) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return generateUrl(pdfApiBaseUrl, options);
    }

    public String generateUrl(String baseUrl, Map<String, String> options) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder apiUrl = new StringBuilder(baseUrl);
        apiUrl.append("key=").append(customerKey);
        if (secretPhrase != null && secretPhrase.trim().length() > 0) {
            apiUrl.append("&hash=").append(calculateHash(options.get("url") + secretPhrase));
        }
        for (String key : options.keySet()) {
            apiUrl.append("&").append(key).append("=").append(URLEncoder.encode(options.get(key), StandardCharsets.UTF_8.toString()));
        }
        return apiUrl.toString();
    }

    private String calculateHash(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text.getBytes());
        return DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
    }
//    public String createThumbnailFromUrl(String url) throws IOException, NoSuchAlgorithmException {
//        String thumbnailUrl;
//        String customerKey = "b4b44d";
//        String secretPhrase = "";//leave secret phrase empty, if not needed
//
//        ScreenshotMachine sm = new ScreenshotMachine(customerKey, secretPhrase);
//
//        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//                "cloud_name", "dkba0jg6y",
//                "api_key", "477891882997543",
//                "api_secret", "XWRfmWrIsCv5sKa3886e5dcPiUc"));
//
//
//        Map options = new HashMap();
//// mandatory parameter
//        options.put("url", url);
//// all next parameters are optional, see our website screenshot API guide for more details
//        options.put("dimension", "1366x768"); // or "1366xfull" for full length screenshot
//        options.put("device", "desktop");
//        options.put("format", "png");
//        options.put("cacheLimit", "0");
//        options.put("delay", "200");
//        options.put("zoom", "100");
//
//        String apiUrl = sm.generateScreenshotApiUrl(options);
//// put link to your html code
//        System.out.println(apiUrl);
//
//// or save screenshot as an image
//        URLConnection openConnection = new URL(apiUrl).openConnection();
//        openConnection.addRequestProperty("User-Agent", "Mozilla/4.0");
//        InputStream in = openConnection.getInputStream();
//
//
//        thumbnailUrl=uploadThumbnail(cloudinary, in);
////        Files.copy(in, Paths.get(output), StandardCopyOption.REPLACE_EXISTING);
////        System.out.println("Screenshot saved as " + output);
//        return thumbnailUrl;
//    }
//
//    public String uploadThumbnail(Cloudinary cloudinary, InputStream in) throws IOException {
//        Map  uploadResult=null;
//        String output = "out.png";
//        File file=new File(output);
//        FileUtils.copyInputStreamToFile(in, file);
//
//        uploadResult=cloudinary.uploader().upload(file, ObjectUtils.asMap(
//
//                "resource_type","auto"
//        ));
//        return String.valueOf(uploadResult.get("url"));
//    }


}