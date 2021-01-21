package ir.dotin.business;

import ir.dotin.exception.InadequateInitialBalanceException;
import ir.dotin.files.BalanceVO;
import ir.dotin.files.PaymentVO;
import ir.dotin.files.TransactionVO;

import java.math.BigDecimal;
import java.util.List;

public class TransactionProcessor {
    static TransactionProcessor trx=new TransactionProcessor();
    static PaymentVO pay = new PaymentVO();

    //Variable Deposit
    private static BigDecimal initialBalance; // موجودی اولیه
    private static depositType depositType;
    private String depositNumber;

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public BigDecimal setInitalBalance(BigDecimal initalBalance) {
        this.initialBalance = initalBalance;
        return initalBalance;
    }

    public depositType getDepositType() {
        return depositType;
    }

    public void setDepositType(depositType depositType) {
        this.depositType = depositType;

    }


    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;

    }

    //------------------------------------------------------

    //Deposit{
    public static boolean doWithdrawTransaction() {
       // PaymentVO pay = new PaymentVO();
      //  TransactionProcessor trx=new TransactionProcessor();
        BigDecimal withdrawAmount = trx.getInitialBalance().subtract(pay.getAmount());
        trx.setInitalBalance(withdrawAmount);
        //return withdrawAmount;
       return true;
    }
//======================================

    //Deposit
    // public List<TransactionVO> doDepositTransaction(TransactionVO transactionVO) {
    public static boolean doDepositTransaction() {
       // PaymentVO pay = new PaymentVO();
       // TransactionProcessor trx=new TransactionProcessor();
        BigDecimal depositAmount = trx.getInitialBalance().add(pay.getAmount());
       trx.setInitalBalance(depositAmount);
   //    return trx.setInitalBalance(depositAmount);

         return true;

    }

    //---------------------------------------------
    public static boolean validateWithdraw(List<BalanceVO> balanceVO) {


        // return getAmount() <= deposit.getInitialBalance();
        //PaymentVO pay = new PaymentVO();

        if(trx.getInitialBalance().compareTo(pay.getAmount()) == 0 ||trx.getInitialBalance().compareTo(pay.getAmount()) == 1)
            return true;
        else
            return false;
    }

    //----------------------------------------------
    public static List<TransactionVO> processPaymentRecord(List<BalanceVO> depositBalances, List<PaymentVO> paymentVOS)
            throws InadequateInitialBalanceException {
        //  List<TransactionProcessor> depositList = TransactionProcessor.getDeposits();

       // BigDecimal debtorBalance = BigDecimal.valueOf(0);
        String debtorBalance = "";
        String creditorBalance = "";
        //   for (TransactionProcessor deposit : depositList) {
        for (PaymentVO paymentVO : paymentVOS) {
            if (!validateWithdraw(depositBalances)) {
                throw new InadequateInitialBalanceException("Not enough balance!");
            } else
                // if (depositType.DEBTOR.equals(deposit.getDepositType())) {
                if (depositType.DEBTOR.equals(paymentVO.getType())) {
                    // debtorBalance = deposit.getInitialBalance();
                    //debtorBalance = paymentVO.getAmount();
                    debtorBalance = String.valueOf(doWithdrawTransaction());
                } else {
                    //  creditorBalance += deposit.getInitialBalance();
                    //  creditorBalance += paymentVO.getAmount();
                    creditorBalance = String.valueOf(doDepositTransaction());
                }
           /* if (debtorBalance > 0 && debtorBalance < creditorBalance) {
                throw new InadequateInitialBalanceException("Not enough balance!");*/
        }

        //      List<TransactionVO> transactionVOS = deposit.doWithdrawTransaction(TransactionVO deposit);
        //     return transactionVOS;

        return null;
    }

}


//-----------------------



