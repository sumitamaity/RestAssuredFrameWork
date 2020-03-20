import com.google.zxing.NotFoundException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Test_01_AssertRedirectedUrl extends Base{
  RequestBuild rb;
  Validation va;

    Base base;
    String uri = URLs.HALO_DOC_DOMAIN;
    String path = URLs.HALO_DOC_URI;
    String file_path = URLs.FILE_PATH;
    String paramName = "url";
    String paramValue = "url=https://www.halodoc.com";


    @BeforeClass
    public void init() {
       rb= new RequestBuild();
       va=new Validation();
    }

   @org.testng.annotations.Test
    public void validateUrl() throws IOException, NotFoundException {

        String uri = URLs.HALO_DOC_DOMAIN;
        String path = URLs.HALO_DOC_URI;
        String file_path = URLs.FILE_PATH;
        String paramName = "url";
        String paramValue = "https://www.halodoc.com";
       // rb = new RequestBuild();
       rb.saveImage(rb.getResponse(rb.buildGetReq(uri, path, paramName, paramValue)));

    }

    @org.testng.annotations.Test
     public void checkRedirection() throws IOException, NotFoundException {
        base = new Base();
        base.initialize();
        URL URL2= new URL("https://www.qrtag.net/api/qr_10.png?url=https://www.halodoc.com");
        System.out.println(URLs.FILE_PATH);
        String destinationUrl=va.decodeQRCode(new File(URLs.FILE_PATH));
       // System.out.println(destinationUrl);
        String extractedUrl= rb.getHeader(rb.buildGetReq(destinationUrl), "Location");
        System.out.println(extractedUrl);

    }

    @Test
    public void SimpleSample() throws ParseException {
        String url = URLs.SIMPLE_URL;
        RequestSpecification rs = RestAssured.given();
        Response res= rs.get(url);
        va.validateJson(res.getBody().asString());
        //System.out.println(res.getBody().asString());
        System.out.println(res.body().jsonPath().getList("error.errors"));

    }
}
