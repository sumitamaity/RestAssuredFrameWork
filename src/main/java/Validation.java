import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Validation {

    public void validateUrlExists(String input, String givenUrl){
        if(input.contains(givenUrl)){
            System.out.println("Url exists");
        }else{
            System.out.println("not found");
        }
    }

    public String readFile(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis= new FileInputStream(file);
        String  res="";
       try{
        res= new String(Files.readAllBytes(Paths.get(path)));
       }catch(IOException e){
           e.printStackTrace();
       }
       return res;
    }

   public String decodeQRCode(File file) throws IOException, NotFoundException {
       // BufferedImage bufferedImage = ImageIO.read(qrCodeImage);
       BufferedImage bufferedImage=ImageIO.read(file);
       LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        System.out.println(result.getText());
        return result.getText();
    }

    public void validateJson(String json) throws ParseException {
        JSONParser jp= new JSONParser();
        Object obj= jp.parse(json);
        JSONObject jo1= (JSONObject)obj;
        JSONObject jo= (JSONObject) jo1.get("error");
        JSONArray ja= (JSONArray) jo.get("errors");
        System.out.println(ja.toString());
        JSONObject JO1= (JSONObject) ja.get(0);
        System.out.println(JO1.get("reason"));
    }
}
