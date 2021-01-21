package ir.dotin;

import ir.dotin.business.TransactionProcessor;
import ir.dotin.files.*;

import java.util.List;

import static ir.dotin.files.BalanceFileHandler.balanceVOs;


public class Main {
    public static final String FILE_PATH_PREFIX = "B://";

    public static void main(String[] args) {
        try {
            List<PaymentVO> paymentVOs = PaymentFileHandler.createPaymentFile("1.10.100.1", "1.20.100.", 1000);
            List<BalanceVO> depositBalances = BalanceFileHandler.createInitialBalanceFile(balanceVOs);
            List<TransactionVO> transactionVOS = TransactionProcessor.processPaymentRecord(depositBalances, paymentVOs);
            TransactionFileHandler.createTransactionFile(transactionVOS);
            BalanceFileHandler.createFinalBalanceFile(depositBalances);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


