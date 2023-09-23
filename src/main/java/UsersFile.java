import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UsersFile {
    public static void main(String[] args) throws IOException, ParseException {
        String filelocation=("./src/main/resources/users.json");
        JSONParser parser=new JSONParser();
        JSONArray users=(JSONArray) parser.parse(new FileReader(filelocation));

        JSONObject jsonobject=new JSONObject();
        jsonobject.put("username","admin");
        jsonobject.put("password","1234");
        jsonobject.put("role","admin");
        JSONObject jsonobj=new JSONObject();
        jsonobj.put("username","salman");
        jsonobj.put("password","1234");
        jsonobj.put("role","student");

        users.add(jsonobject);
        users.add(jsonobj);

        FileWriter writer=new FileWriter(filelocation);
        writer.write(users.toJSONString());
        writer.flush();
        writer.close();
    }
}
