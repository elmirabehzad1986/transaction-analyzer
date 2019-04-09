package elmira.transactionanalyser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimeBasedBalanceAnalyzer implements AccountBalanceAnalyzer {

    TreeSet<Transaction> transactions;
    Set<String> reversalTransactionIds;

    public TimeBasedBalanceAnalyzer(List<Transaction> transactions) {
        this.transactions = new TreeSet<>();
        this.transactions.addAll(transactions);
        this.reversalTransactionIds = this.transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.REVERSAL)
                .map(Transaction::getRelatedTransaction)
                .collect(Collectors.toSet());
    }

    @Override
    public AnalyzerOutput analyse(String accountId, LocalDateTime from, LocalDateTime to) {

        Transaction t1 = new Transaction(null,null,null,from,null,null,null);
        Transaction t2 = new Transaction(null,null,null,to,null,null,null);

        Transaction tFrom = transactions.higher(t1);
        Transaction tTo = transactions.lower(t2);

        List<Transaction> transactionWindow = transactions.subSet(tFrom, tTo).stream()
                .filter(t -> (t.getFromAccountId().equalsIgnoreCase(accountId)
                        || t.getToAccountId().equalsIgnoreCase(accountId))
                        && (t.getCreateAt().isAfter(from)
                        && t.getCreateAt().isBefore(to))
                        && !this.reversalTransactionIds.contains(t.getTransactionId()))
                .map(t -> {
                    if (t.getFromAccountId().equalsIgnoreCase(accountId)) {
                        return new Transaction(t.getTransactionId(), t.getFromAccountId(), t.getToAccountId(), t.getCreateAt()
                                , t.getAmount().negate(), t.getTransactionType(), t.getRelatedTransaction());
                    }
                    return t;
                })
                .collect(Collectors.toList());
        BigDecimal sumOfFund = transactionWindow.stream().map(Transaction::getAmount).reduce((a, b) -> a.add(b)).get();
        AnalyzerOutput output = new AnalyzerOutput(sumOfFund, transactionWindow.stream().count());

        return output;
    }
}
