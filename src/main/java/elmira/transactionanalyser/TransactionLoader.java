package elmira.transactionanalyser;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TransactionLoader {

    private String filePath;

    public TransactionLoader(String filePath) {
        this.filePath = filePath;
    }

    public List<Transaction> convert() throws IOException {

        final int COLUMN_NUMBER = 7;
        final String TRANSACTION_ID = "transactionId";
        final String FROM_ACC_ID = "fromAccountId";
        final String TO_ACC_ID = "toAccountId";
        final String CREATE_AT = "createdAt";
        final String TRANSACTION_TYPE = "transactionType";
        final String AMOUNT = "amount";
        final String RELATED_TRANSACTION = "relatedTransaction";

        List<Transaction> transactions = new ArrayList<>();

        File file = new File(this.filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        Map<String, Integer> headerToIndex = new HashMap<>();

        while((line = br.readLine()) != null) {

            String[] fields = line.split(",");

            if(headerToIndex.isEmpty()) {
                if(fields.length != COLUMN_NUMBER){
                    continue;
                }
                for(int i = 0; i < fields.length; ++i) {
                    headerToIndex.put(fields[i], i);
                }
            }

            if(fields.length != COLUMN_NUMBER && fields.length != COLUMN_NUMBER-1){
                // TODO: Log invalid format line here
                continue;
            }

            try {
                // TODO: Validate CSV fields
                String transactionId = fields[headerToIndex.get(TRANSACTION_ID)];
                String fromAccountId = fields[headerToIndex.get(FROM_ACC_ID)];
                String toAccountId = fields[headerToIndex.get(TO_ACC_ID)];
                LocalDateTime createAt = LocalDateTime.parse(fields[headerToIndex.get(CREATE_AT)], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                TransactionType transactionType = fields[headerToIndex.get(TRANSACTION_TYPE)].trim().equalsIgnoreCase("REVERSAL")
                        ? TransactionType.REVERSAL : TransactionType.PAYMENT;
                BigDecimal amount = new BigDecimal(fields[headerToIndex.get(AMOUNT)]);
                String relatedTransaction = fields.length == COLUMN_NUMBER ? fields[headerToIndex.get(RELATED_TRANSACTION)] : null;

                Transaction transaction = new Transaction(transactionId, fromAccountId, toAccountId, createAt, amount, transactionType, relatedTransaction);
                transactions.add(transaction);

            }catch (Exception e){ // TODO: Use correct exceptions
                // TODO: Log invalid format line
            }
        }

        return transactions;
    }

}
