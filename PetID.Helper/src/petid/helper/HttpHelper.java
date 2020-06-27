/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petid.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author TNT
 */
public class HttpHelper {

    public static InputStream getInputStream(String url) throws MalformedURLException, IOException {
        return new URL(url).openStream();
    }

    public static String getPageContent(String url) throws IOException {
        StringBuilder sb = new StringBuilder("");
        try (InputStream is = getInputStream(url);
                InputStreamReader isReader = new InputStreamReader(is, "UTF-8");
                BufferedReader bReader = new BufferedReader(isReader)) {
            String line;
            while ((line = bReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
    }
}
