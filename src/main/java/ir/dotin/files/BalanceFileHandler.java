package ir.dotin.files;

import ir.dotin.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ir.dotin.files.PaymentFileHandler.generateRandomAmount;

public class BalanceFileHandler {
    private static final String BALANCE_FILE_PATH = Main.FILE_PATH_PREFIX + "Balance.txt";
    public static List<BalanceVO> balanceVOs = new ArrayList<>();
    static String debtorDepositNumber;
    static String creditorDepositNumberPrefix;


    public static List<BalanceVO> createInitialBalanceFile(List<BalanceVO> balanceVOs) throws IOException, ClassNotFoundException {
        BalanceVO balanceVO=new BalanceVO();
        balanceVOs.add(new BalanceVO(debtorDepositNumber, generateRandomAmount()));
        for (int i = 1; i <= 1000; i++) {
            balanceVOs.add(new BalanceVO(creditorDepositNumberPrefix, generateRandomAmount()));
        }
        writeBalanceRecordsToFile(balanceVOs);
        // read and output serialize
//----------------------------------------------------------
       FileInputStream balanceIn = new FileInputStream(BALANCE_FILE_PATH);
        ObjectInputStream in = new ObjectInputStream(balanceIn);
        balanceVO = (BalanceVO) in.readObject();
        System.out.println(balanceVO);
        in.close();
        balanceIn.close();
//----------------------------------------------------------
     /*   // read and output
        FileReader balanceReader = new FileReader(BALANCE_FILE_PATH);
        BufferedReader br = new BufferedReader(balanceReader);
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        balanceReader.close();*/

        return balanceVOs;
    }

    //--------------------------------
    private static void writeBalanceRecordsToFile(List<BalanceVO> balanceVOs) throws IOException {
//----------------------------
//serialize
        FileOutputStream Bout=new   FileOutputStream(BALANCE_FILE_PATH);
        ObjectOutputStream   balanceOut=new  ObjectOutputStream(Bout);
//----------------------------
        //  PrintWriter printWriter = new PrintWriter(PAYMENT_FILE_PATH);
        // FileWriter fileWriter = new FileWriter(PAYMENT_FILE_PATH, true);
        for (BalanceVO balanceVO : balanceVOs) {
            //  printWriter.println(paymentRecord.toString());
            balanceOut.writeObject(balanceVO);
        }

        //  printWriter.close();
        balanceOut.close();
    }
//---------------------------------------------------------------------------------
 /*       try (PrintWriter printWriter = new PrintWriter(BALANCE_FILE_PATH)) {
            for (BalanceVO balanceVO : balanceVOs) {
                printWriter.println(balanceVO.toString());
            }
            // printWriter.close();
        }
    }*/

    //----------------------------------------
    public static String createFinalBalanceFile(List<BalanceVO> depositBalances)
            throws IOException {
        String resultFinalBalance = "";

        Path pathBalanceUpdate = Paths.get(Main.FILE_PATH_PREFIX + "BalanceUpdate.txt");
        Files.createFile(pathBalanceUpdate);
        writeBalanceRecordsToFile(balanceVOs);
        resultFinalBalance += debtorDepositNumber + "\t" + creditorDepositNumberPrefix + "\t" + depositBalances + "\n";

        return resultFinalBalance;
    }

//----------------------------------------------------------------------------------------


}




