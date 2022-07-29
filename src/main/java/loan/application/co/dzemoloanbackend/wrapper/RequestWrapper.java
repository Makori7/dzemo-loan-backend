package loan.application.co.dzemoloanbackend.wrapper;

import loan.application.co.dzemoloanbackend.entity.Account;
import lombok.Data;

@Data
public class RequestWrapper {

    private String accountFrom;
    private String accountTo;
    private double amount;
    private String narration;
    private String externalReference;
}
