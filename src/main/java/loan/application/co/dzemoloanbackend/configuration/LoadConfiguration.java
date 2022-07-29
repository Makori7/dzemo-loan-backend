package loan.application.co.dzemoloanbackend.configuration;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import loan.application.co.dzemoloanbackend.entity.Account;
import loan.application.co.dzemoloanbackend.entity.AccountClass;
import loan.application.co.dzemoloanbackend.entity.Customer;
import loan.application.co.dzemoloanbackend.services.CrudOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
public class LoadConfiguration {

    @Autowired
    CrudOperation crudOperation;

    @Resource
    Environment environment;

  //  @PostConstruct
    public void init(){
        createAccountClass();
        createCustomers();
        createCustomers();
    }

    public void createAccountClass(){

        JsonElement array = loadJsonFileSystemData(environment.getProperty("configs.AccountClass"));
        List<AccountClass> accountClassList =new ArrayList<>();

        accountClassList = g().fromJson(array, new TypeToken<List<AccountClass>>() {
        }.getType());

        crudOperation.createAccountClass(accountClassList);
    }

    public void createCustomers(){

        JsonArray array = loadJsonFileSystemData(environment.getProperty("configs.customers")).getAsJsonArray();

        for (JsonElement e: array){
            crudOperation.createCustomer(g().fromJson(e, Customer.class));
        }

    }

    public void createCustomerAccounts(){

        JsonArray array = loadJsonFileSystemData(environment.getProperty("configs.createAccounts")).getAsJsonArray();
        for (JsonElement e: array){
            crudOperation.createAccount(g().fromJson(e, Account.class));
        }
    }

    public JsonElement loadJsonFileSystemData(String fileName){
        JsonElement jsonElement = null;
        InputStream is = null;
        try {
            is = Files.newInputStream(Paths.get(fileName));
            jsonElement =  JsonParser.parseReader(new InputStreamReader(Objects.requireNonNull(is),
                    StandardCharsets.UTF_8));
        }catch (Exception e ){
           // logger.info("failed to load file system "+fileName);
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
             //       logger.info(e.getMessage());
                }
            }
        }
        return jsonElement;
    }

    Gson g(){
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
            Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }).create();
    }
}
