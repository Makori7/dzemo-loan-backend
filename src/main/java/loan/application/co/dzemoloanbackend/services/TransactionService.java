package loan.application.co.dzemoloanbackend.services;

import loan.application.co.dzemoloanbackend.wrapper.RequestWrapper;
import loan.application.co.dzemoloanbackend.wrapper.UniversalResponseWrapper;

public interface TransactionService {

    UniversalResponseWrapper fundsTransfer(RequestWrapper requestWrapper);
    UniversalResponseWrapper loanApplication(RequestWrapper requestWrapper);
    UniversalResponseWrapper loanRepayment(RequestWrapper requestWrapper);

}
