package ir.dotin;

import ir.dotin.business.TransactionProcessor;
import ir.dotin.files.*;

import java.util.List;

import static ir.dotin.files.BalanceFileHandler.balanceVOs;


public class Main {
    public static final String FILE_PATH_PREFIX = "E://";
    public static final String DEBTOR_DEPOSIT_NUMBER = "1.10.100.1";
    public static final String CREDITOR_DEPOSIT_NUMBER_PREFIX = "1.20.100.";
    public static final int CREDITOR_COUNT = 1000;

    public static void main(String[] args) {
        try {
            List<PaymentVO> paymentVOs = PaymentFileHandler.createPaymentFile(DEBTOR_DEPOSIT_NUMBER, CREDITOR_DEPOSIT_NUMBER_PREFIX, CREDITOR_COUNT);
            List<BalanceVO> depositBalances = BalanceFileHandler.createInitialBalanceFile(balanceVOs);
            List<TransactionVO> transactionVOS = TransactionProcessor.processPaymentRecords(depositBalances, paymentVOs);
            TransactionFileHandler.createTransactionFile(transactionVOS);
            BalanceFileHandler.createFinalBalanceFile(depositBalances);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


