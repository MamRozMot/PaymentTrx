package ir.dotin.files;

import ir.dotin.Main;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ir.dotin.business.DepositType.CREDITOR;
import static ir.dotin.business.DepositType.DEBTOR;

public class PaymentFileHandler {
    // private static final String DEBTOR = "debtor";
    // private static final String CREDITOR = "creditor";
    private static final int MIN_AMOUNT = 100;
    private static final int MAX_AMOUNT = 10000;
    private static final String PAYMENT_FILE_PATH = Main.FILE_PATH_PREFIX + "Payment.txt";
    private static final Random random = new Random();

    public static List<PaymentVO> createPaymentFile(String debtorDepositNumber, String creditorDepositNumberPrefix, int creditorCount) throws IOException, ClassNotFoundException {
        List<PaymentVO> paymentVOs = new ArrayList<>();
        //  PaymentVO paymentVO=new PaymentVO();
        paymentVOs.add(new PaymentVO(DEBTOR, debtorDepositNumber, generateRandomAmount()));
        for (int i = 1; i <= creditorCount; i++) {
            paymentVOs.add(new PaymentVO(CREDITOR, creditorDepositNumberPrefix + i, generateRandomAmount()));

        }
        writePaymentRecordsToFile(paymentVOs);
        // read and output serialize
//----------------------------------------------------------
        FileInputStream payIn = new FileInputStream(PAYMENT_FILE_PATH);
        ObjectInputStream in = new ObjectInputStream(payIn);
        // paymentVO = (PaymentVO) in.readObject();
        paymentVOs.add((PaymentVO) in.readObject());
        System.out.println(paymentVOs);
        in.close();
        payIn.close();
//----------------------------------------------------------
       /* FileReader payReader = new FileReader(PAYMENT_FILE_PATH);
        BufferedReader pr = new BufferedReader(payReader);
        String s;
        while ((s = pr.readLine()) != null) {
            System.out.println(s);
        }
        payReader.close();*/
        return paymentVOs;

    }


    public static BigDecimal generateRandomAmount() {
        BigDecimal rand = BigDecimal.valueOf(0);

        rand = BigDecimal.valueOf(random.nextInt((MAX_AMOUNT - MIN_AMOUNT) + 1) + MIN_AMOUNT);
        return rand;
    }


    private static void writePaymentRecordsToFile(List<PaymentVO> paymentVOs) throws IOException {
//----------------------------
//serialize
        FileOutputStream pout = new FileOutputStream(PAYMENT_FILE_PATH);
        ObjectOutputStream payOut = new ObjectOutputStream(pout);
//----------------------------
        //  PrintWriter printWriter = new PrintWriter(PAYMENT_FILE_PATH);
        // FileWriter fileWriter = new FileWriter(PAYMENT_FILE_PATH, true);
        for (PaymentVO paymentVO : paymentVOs) {
            //  printWriter.println(paymentRecord.toString());
            payOut.writeObject(paymentVO);
        }

        //  printWriter.close();
        payOut.close();
    }


}