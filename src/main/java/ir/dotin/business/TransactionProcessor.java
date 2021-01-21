package ir.dotin.business;

import ir.dotin.exception.InadequateInitialBalanceException;
import ir.dotin.files.BalanceVO;
import ir.dotin.files.PaymentVO;
import ir.dotin.files.TransactionVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionProcessor {

    public static List<TransactionVO> processPaymentRecords(List<BalanceVO> depositBalances, List<PaymentVO> paymentVOs) throws Exception {
        String debtorDepositNumber = getDebtorDepositNumber(paymentVOs);
        validateDebtorBalance(depositBalances, paymentVOs, debtorDepositNumber);

        List<TransactionVO> transactionVOs = new ArrayList<>();
        for (PaymentVO paymentVO : paymentVOs) {
            if (DepositType.CREDITOR.equals(paymentVO.getType())) {
                transactionVOs.add(processPayment(depositBalances, debtorDepositNumber, paymentVO));
            }
        }
        return transactionVOs;
    }

    private static String getDebtorDepositNumber(List<PaymentVO> paymentVOs) throws Exception {
        for (PaymentVO paymentVO : paymentVOs) {
            if (DepositType.DEBTOR.equals(paymentVO.getType())) {
                return paymentVO.getDepositNumber();
            }
        }
        throw new Exception("Debtor deposit not found!");
    }

    private static void validateDebtorBalance(List<BalanceVO> depositBalances, List<PaymentVO> paymentVOs, String debtorDepositNumber) throws Exception {
        BigDecimal totalCreditorAmount = getCreditorAmountsSum(paymentVOs);
        BigDecimal debtorBalance = getBalance(depositBalances, debtorDepositNumber);
        if (debtorBalance == null)
            throw new Exception("Debtor balance not found!");
        if (totalCreditorAmount.compareTo(debtorBalance) > 0)
            throw new InadequateInitialBalanceException("Not enough balance!");
    }

    private static BigDecimal getCreditorAmountsSum(List<PaymentVO> paymentVOs) {
        BigDecimal totalCreditorAmount = new BigDecimal(0);
        for (PaymentVO paymentVO : paymentVOs) {
            if (DepositType.CREDITOR.equals(paymentVO.getType())) {
                totalCreditorAmount.add(paymentVO.getAmount());
            }
        }
        return totalCreditorAmount;
    }

    private static BigDecimal getBalance(List<BalanceVO> depositBalances, String depositNumber) {
        for (BalanceVO balanceVO : depositBalances) {
            if (balanceVO.getDepositNumber().equals(depositNumber))
                return balanceVO.getAmount();
        }
        return null;
    }

    private static TransactionVO processPayment(List<BalanceVO> depositBalances, String debtorDepositNumber, PaymentVO creditorPaymentVO) {
        TransactionVO transactionVO = new TransactionVO();
        transactionVO.setDebtorDepositNumber(debtorDepositNumber);
        transactionVO.setCreditorDepositNumber(creditorPaymentVO.getDepositNumber());
        transactionVO.setAmount(creditorPaymentVO.getAmount());
        for (BalanceVO balanceVO : depositBalances) {
            if (balanceVO.getDepositNumber().equals(creditorPaymentVO.getDepositNumber())) {//Creditor
                balanceVO.setAmount(balanceVO.getAmount().add(creditorPaymentVO.getAmount()));
            } else if (balanceVO.getDepositNumber().equals(debtorDepositNumber)) {//Debtor
                balanceVO.setAmount(balanceVO.getAmount().subtract(creditorPaymentVO.getAmount()));
            }
        }
        return transactionVO;
    }

}




