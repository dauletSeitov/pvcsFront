package tools;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class Utils {

    public static <T> Response<T> GET(String additionalUrl, Class<T> classOfT) {
        return GET(additionalUrl, classOfT, null);
    }

    public static <T> Response<T> GET(String additionalUrl, Class<T> classOfT, Map<String, String> headers) {

        try {
            String url = Constants.BASE_URL + additionalUrl;
            System.out.println("Befor GET: " + url);
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);

            // add request header
            request.setHeader("Content-Type", "application/json");
            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    request.setHeader(header.getKey(), header.getValue());
                }
            }

            HttpResponse response = client.execute(request);

            int responseCode = response.getStatusLine().getStatusCode();

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            String responseRow = result.toString();
            System.out.println("After Http Code: {" + responseCode + "} response: " + responseRow);
            T object = new Gson().fromJson(responseRow, classOfT);

            final Response gotObject = new Response(object, responseCode == 200);


            if (gotObject.getResponse() == null) {
                return null;
            } else {
                return gotObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static <T> Response<T> POST(String additionalUrl, Object to) {
        return POST(additionalUrl, to, null, null);

    }

    public static <T> Response<T> POST(String additionalUrl, Object to, Class<T> classFrom) {
        return POST(additionalUrl, to, classFrom, null);
    }

    public static <T> Response<T> POST(String additionalUrl, Object to, Class<T> classFrom, Map<String, String> headers) {

        try {

            HttpClient client = HttpClientBuilder.create().build();


            String url = Constants.BASE_URL + additionalUrl;
            System.out.println("Befor POST: " + url);
            HttpPost post = new HttpPost(url);

            post.setHeader("Content-Type", "application/json");
            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    post.setHeader(header.getKey(), header.getValue());
                }
            }

            Gson gson = new Gson();

            post.setEntity(new StringEntity(gson.toJson(to)));

            HttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();


            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            String responseRow = result.toString();
            System.out.println("After Http Code: {" + responseCode + "} response: " + responseRow);
            if (classFrom == null)
                return null;
            T object = gson.fromJson(responseRow, classFrom);

            final Response gotObject = new Response(object, responseCode == 200);
            if (gotObject.getResponse() == null) {
                return null;
            } else {
                return gotObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isEmpty(String str){
        return str == null || str.trim().isEmpty();

    }
}



