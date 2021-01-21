package ir.dotin.files;

import ir.dotin.Main;
import ir.dotin.business.TransactionProcessor;

import java.io.*;
import java.util.List;

import static ir.dotin.files.TransactionVO.creditorDepositNumber;
import static ir.dotin.files.TransactionVO.debtorDepositNumber;

public class TransactionFileHandler {
    private static final String TRANSACTION_FILE_PATH = Main.FILE_PATH_PREFIX + "Transactions.txt";

    //createTransactionFile
    public static void createTransactionFile(List<TransactionVO> list) throws IOException, ClassNotFoundException {
        TransactionVO transactionVO = new TransactionVO();
        TransactionProcessor transaction = new TransactionProcessor();
        // String debtorDepositNumber = "";
        String resultText = "";
        //write serialize
        FileOutputStream Tout = new FileOutputStream(TRANSACTION_FILE_PATH);
        ObjectOutputStream transactionOut = new ObjectOutputStream(Tout);
//-----------------------------------------------------------
        //  BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE_PATH));
        for (TransactionProcessor deposit : list) {
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

        }
    }
}

