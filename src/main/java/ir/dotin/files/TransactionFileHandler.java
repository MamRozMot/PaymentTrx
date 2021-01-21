package ir.dotin.files;

import ir.dotin.PaymentTransactionApp;
import ir.dotin.business.TransactionProcessor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class TransactionFileHandler {
    //createTransactionFile
    public static void createTransactionFile(List<TransactionVO> list) throws IOException, ClassNotFoundException {
        TransactionVO transactionVO = new TransactionVO();
        TransactionProcessor transaction = new TransactionProcessor();
        // String debtorDepositNumber = "";
        String resultText = "";
        //write serialize
        FileOutputStream Tout = new FileOutputStream(PaymentTransactionApp.TRANSACTION_FILE_PATH);
        ObjectOutputStream transactionOut = new ObjectOutputStream(Tout);
//-----------------------------------------------------------
        //  BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE_PATH));
        /*for (TransactionProcessor deposit : list) {
            // if ("debtor".equals(deposit.getDepositType()))
            if (transaction.doWithdrawTransaction())
                debtorDepositNumber = deposit.getDepositNumber();
            else if (transaction.doDepositTransaction())
                creditorDepositNumber = deposit.getDepositNumber();
            resultText += debtorDepositNumber + "\t" + creditorDepositNumber + "\t" + deposit.getInitialBalance() + "\n";

            transactionOut.writeObject(resultText);
            //   writer.write(resultText);
            //  writer.newLine();
            transactionOut.close();
//--------------------------------
            // read and output serialize
            FileInputStream transactionIn = new FileInputStream(TRANSACTION_FILE_PATH);
            ObjectInputStream in = new ObjectInputStream(transactionIn);
            transactionVO = (TransactionVO) in.readObject();
            System.out.println(transactionVO);
            in.close();
            transactionIn.close();

        }*/
    }
}

