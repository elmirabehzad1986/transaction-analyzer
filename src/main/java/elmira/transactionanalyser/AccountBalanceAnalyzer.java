package elmira.transactionanalyser;

import java.time.LocalDateTime;

public interface AccountBalanceAnalyzer {


    AnalyzerOutput analyse(String accountId, LocalDateTime from, LocalDateTime to);
}
