package elmira.transactionanalyser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class AnalyzerContext {

    private List<Transaction> transactions;




    public int reload(String filePath) throws IOException {

        TransactionLoader transactionLoader = new TransactionLoader(filePath);
        transactions = transactionLoader.convert();

        return transactions.size();
    }

    public AnalyzerOutput analyse(String accountId, LocalDateTime from, LocalDateTime to) {

        AccountBalanceAnalyzer timeBasedBalanceAnalyzer = new TimeBasedBalanceAnalyzer(transactions);
        AnalyzerOutput output = timeBasedBalanceAnalyzer.analyse(accountId, from, to);

        return output;
    }
}
