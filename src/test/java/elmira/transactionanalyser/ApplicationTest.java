package elmira.transactionanalyser;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class ApplicationTest {
    ;

    @Test
    public void analyse() throws IOException {

        String filePath = getClass().getResource("/transactions.csv").getPath();

        int numberOfTransactions = 0;

        AnalyzerContext analyzerContex = new AnalyzerContext();
        numberOfTransactions = analyzerContex.reload(filePath);
        Assert.assertEquals(5, numberOfTransactions);


        String accountId = "ACC334455";
        LocalDateTime from = LocalDateTime.parse("2018-10-20T12:00:00");
        LocalDateTime to = LocalDateTime.parse("2018-10-20T19:00:00");
        AnalyzerOutput analysis = analyzerContex.analyse(accountId, from, to);
        Assert.assertEquals(new BigDecimal("-25.00"), analysis.getRelativeAccountBalance());
        Assert.assertEquals(1, analysis.getNumberOfTransactions());
    }
}
