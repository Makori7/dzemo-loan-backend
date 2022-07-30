package loan.application.co.dzemoloanbackend.controller;

import loan.application.co.dzemoloanbackend.services.TransactionService;
import loan.application.co.dzemoloanbackend.wrapper.FetchWrapper;
import loan.application.co.dzemoloanbackend.wrapper.RequestWrapper;
import loan.application.co.dzemoloanbackend.wrapper.UniversalResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/funds-transfer")
    public UniversalResponseWrapper transferFunds(@RequestBody RequestWrapper requestWrapper){
        return transactionService.fundsTransfer(requestWrapper);
    }

    @PostMapping("/loan-application")
    public UniversalResponseWrapper loanApplication(@RequestBody RequestWrapper requestWrapper){
        return transactionService.loanApplication(requestWrapper);
    }

    @PostMapping("/loan-repayment")
    public UniversalResponseWrapper loanRepayment(@RequestBody RequestWrapper requestWrapper){
        return transactionService.loanRepayment(requestWrapper);
    }

    @PostMapping("/fetch-transaction-history")
    public UniversalResponseWrapper fetchTransactionHistory(@RequestBody FetchWrapper fetchWrapper){
        return transactionService.fetchTransactionHistory(fetchWrapper);
    }



}
