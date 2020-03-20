import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestBuild {

    public RequestSpecification buildGetReq(String uri, String path, String paramName, String paramValue){
        RequestSpecification req= RestAssured.given();
        Map<String, String> mp= new HashMap<String, String>();
        req.baseUri(uri);
        if(path!="");
        req.basePath(path);

        if(paramName!=""&& paramValue!="")
        {
            mp.put(paramName, paramValue);
            req.params(mp);
        }
        return req;
    }
    public Response buildGetReq(String url){
        RequestSpecification req=RestAssured.given();
        req.redirects().follow(false);
        Response response= req.get(url);
        return response;
    }
        public Response getResponse(RequestSpecification req){
        Response response = req.get();
       return response;
    }

    public void saveImage(Response response) throws IOException {
      byte a[]= response.asByteArray();

      String file_path=URLs.FILE_PATH;
      File file = new File(file_path);
        FileOutputStream fout= new FileOutputStream(file);
        //decodeImage(a,fout);
       //byte [] b= Base64.decodeBase64(a);
       // decodeImage(a);
        fout.write(a);
        fout.close();

    }

    public void decodeImage(byte b[]){
        //b= Base64.getDecoder().decode(String.valueOf(fout1));
        b= Base64.decodeBase64(b);

    }


    public String getHeader(Response response, String headerAttribute){
        String header= response.getHeader(headerAttribute);
        return header;
    }
    public Response sendPostReq(String Url, Map<String, ?> headers, String body) {

        RequestSpecification request=RestAssured.given();
        request.headers(headers);
        request.body(body);
        Response response= request.post(Url);
        return response;

    }

}
