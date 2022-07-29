package loan.application.co.dzemoloanbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("loan.application.co.dzemoloanbackend.entity")
public class DzemoLoanBackendApplication {



    public static void main(String[] args) {
        SpringApplication.run(DzemoLoanBackendApplication.class, args);
    }

}
