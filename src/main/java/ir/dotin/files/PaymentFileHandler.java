package ir.dotin.files;

import ir.dotin.Main;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ir.dotin.business.DepositType.CREDITOR;
import static ir.dotin.business.DepositType.DEBTOR;

public class PaymentFileHandler {
    private static final int MIN_AMOUNT = 100;
    private static final int MAX_AMOUNT = 10000;
    private static final String PAYMENT_FILE_PATH = Main.FILE_PATH_PREFIX + "Payment.txt";
    private static final Random random = new Random();

    public static List<PaymentVO> createPaymentFile(String debtorDepositNumber, String creditorDepositNumberPrefix, int creditorCount) throws IOException, ClassNotFoundException {
        List<PaymentVO> paymentVOs = new ArrayList<>();
        paymentVOs.add(new PaymentVO(DEBTOR, debtorDepositNumber, generateRandomAmount()));
        for (int i = 1; i <= creditorCount; i++) {
            paymentVOs.add(new PaymentVO(CREDITOR, creditorDepositNumberPrefix + i, generateRandomAmount()));
        }
        writePaymentRecordsToFile(paymentVOs);
        printPaymentVOsToConsole(paymentVOs);
        return paymentVOs;

    }

    public static BigDecimal generateRandomAmount() {
        return BigDecimal.valueOf(random.nextInt((MAX_AMOUNT - MIN_AMOUNT) + 1) + MIN_AMOUNT);
    }

    private static void printPaymentVOsToConsole(List<PaymentVO> paymentVOs) {
        System.out.println("========================= PAYMENTS =========================");
        for (PaymentVO paymentVO : paymentVOs)
            System.out.println(paymentVO.toString());
        System.out.println("============================================================");
    }

    private static void writePaymentRecordsToFile(List<PaymentVO> paymentVOs) throws IOException {
        PrintWriter printWriter = new PrintWriter(PAYMENT_FILE_PATH);
        for (PaymentVO paymentVO : paymentVOs) {
            printWriter.println(paymentVO.toString());
        }
        printWriter.close();
    }

}