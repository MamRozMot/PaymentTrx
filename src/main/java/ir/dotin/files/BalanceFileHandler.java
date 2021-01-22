package ir.dotin.files;

import ir.dotin.PaymentTransactionApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BalanceFileHandler {
    public static List<BalanceVO> balanceVOs = new ArrayList<>();


    public static List<BalanceVO> createInitialBalanceFile(List<BalanceVO> balanceVOs) throws IOException {
//--------------------------------------------------------------------------
        //To Test Transaction Processor
        String input1 = "10000000";
        BigDecimal b = new BigDecimal(input1);
        BigDecimal a = PaymentTransactionApp.generateRandomAmount().add(b);
        balanceVOs.add(new BalanceVO(PaymentTransactionApp.DEBTOR_DEPOSIT_NUMBER, a));
//---------------------------------------------------------------------------
        // balanceVOs.add(new BalanceVO(PaymentTransactionApp.DEBTOR_DEPOSIT_NUMBER, PaymentTransactionApp.generateRandomAmount()));
        for (int i = 1; i <= 1000; i++) {
            balanceVOs.add(new BalanceVO(PaymentTransactionApp.CREDITOR_DEPOSIT_NUMBER_PREFIX + i, PaymentTransactionApp.generateRandomAmount()));
        }
        writeBalanceVOToFile(balanceVOs);
        printBalanceVOsToConsole(balanceVOs);
        return balanceVOs;
    }

    //----------------------------------------------------------
      /*  // read and output serialize
       FileInputStream balanceIn = new FileInputStream(PaymentTransactionApp.BALANCE_FILE_PATH);
        ObjectInputStream in = new ObjectInputStream(balanceIn);
        BalanceVO balanceVO = (BalanceVO) in.readObject();
        System.out.println(balanceVO);
        in.close();
        balanceIn.close();*/
//----------------------------------------------------------
    private static void writeBalanceVOToFile(List<BalanceVO> balanceVOs) throws IOException {
        PrintWriter printWriter = new PrintWriter(PaymentTransactionApp.BALANCE_FILE_PATH);
        for (BalanceVO balanceVO : balanceVOs) {
            printWriter.println(balanceVO.toString());
        }
        printWriter.close();
    }
//-----------------------------------------------------------
//serialize
    //   FileOutputStream Bout=new   FileOutputStream(PaymentTransactionApp.BALANCE_FILE_PATH);
    // ObjectOutputStream   balanceOut=new  ObjectOutputStream(Bout);
    // FileWriter fileWriter = new FileWriter(PAYMENT_FILE_PATH, true);
    // for (BalanceVO balanceVO : balanceVOs) {
    //  balanceOut.writeObject(balanceVO);
    //  }
    // balanceOut.close();
//---------------------------------------------------------------------------------

    private static void printBalanceVOsToConsole(List<BalanceVO> balanceVOS) {
        System.out.println("========================= BALANCE =========================");
        for (BalanceVO balanceVO : balanceVOS)
            System.out.println(balanceVO.toString());
        System.out.println("============================================================");
    }

    //-------------------------------------------------------------------------------------
    public static String createFinalBalanceFile(List<BalanceVO> depositBalances)
            throws IOException {
        String resultFinalBalance = "";
        Path pathBalanceUpdate = Paths.get(PaymentTransactionApp.FILE_PATH_PREFIX + "BalanceUpdate.txt");
        Files.createFile(pathBalanceUpdate);
        writeFinalBalanceVOToFile(balanceVOs);
        resultFinalBalance += PaymentTransactionApp.DEBTOR_DEPOSIT_NUMBER + "\t" + PaymentTransactionApp.CREDITOR_DEPOSIT_NUMBER_PREFIX + "\t" + depositBalances + "\n";
        printFinalBalanceVOsToConsole(balanceVOs);
        return resultFinalBalance;
    }

    private static void writeFinalBalanceVOToFile(List<BalanceVO> balanceVOs) throws IOException {
        PrintWriter printWriter = new PrintWriter(PaymentTransactionApp.BALANCE_UPDATE_FILE_PATH);
        for (BalanceVO balanceVO : balanceVOs) {
            printWriter.println(balanceVO.toString());
        }
        printWriter.close();
    }

    private static void printFinalBalanceVOsToConsole(List<BalanceVO> balanceVOS) {
        System.out.println("========================= FinalBALANCE =========================");
        for (BalanceVO balanceVO : balanceVOS)
            System.out.println(balanceVO.toString());
        System.out.println("============================================================");
    }
}




