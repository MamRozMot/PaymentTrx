package ir.dotin.files;

import ir.dotin.PaymentTransactionApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TransactionFileHandler {
    //createTransactionFile
    public static String createTransactionFile(List<TransactionVO> transactionVOS, List<BalanceVO> depositBalances) throws IOException {
        String resultTransaction = "";

        Path pathTransaction = Paths.get(PaymentTransactionApp.FILE_PATH_PREFIX + "Transactions.txt");
        Files.createFile(pathTransaction);
        writeTransactionVOToFile(transactionVOS);
        //if()
        resultTransaction += PaymentTransactionApp.DEBTOR_DEPOSIT_NUMBER + " " + PaymentTransactionApp.CREDITOR_DEPOSIT_NUMBER_PREFIX + "\t" + depositBalances + "\n";
        printTransactionVOsToConsole(transactionVOS);
        return resultTransaction;
    }

    //------------------------------------
    private static void writeTransactionVOToFile(List<TransactionVO> transactionVOS) throws IOException {
        PrintWriter printWriter = new PrintWriter(PaymentTransactionApp.TRANSACTION_FILE_PATH);
        for (TransactionVO transactionVO : transactionVOS) {
            printWriter.println(transactionVO.toString());
        }
        printWriter.close();
    }

    //----------------------------------------------------------------
    private static void printTransactionVOsToConsole(List<TransactionVO> transactionVOS) {
        System.out.println("========================= TRANSACTION =========================");
        for (TransactionVO transactionVO : transactionVOS)
            System.out.println(transactionVO.toString());
        System.out.println("============================================================");
    }
//-----------------------------------------------------------------
/*        TransactionVO transactionVO = new TransactionVO();
        TransactionProcessor transaction = new TransactionProcessor();
        // String debtorDepositNumber = "";
        String resultText = "";
        //write serialize
        FileOutputStream Tout = new FileOutputStream(PaymentTransactionApp.TRANSACTION_FILE_PATH);
        ObjectOutputStream transactionOut = new ObjectOutputStream(Tout);
//-----------------------------------------------------------*/
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


