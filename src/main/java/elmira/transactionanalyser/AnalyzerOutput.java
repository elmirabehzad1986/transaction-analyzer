package elmira.transactionanalyser;

import java.math.BigDecimal;
import java.util.Objects;

public class AnalyzerOutput {

    private BigDecimal relativeAccountBalance;
    private long numberOfTransactions;

    public AnalyzerOutput(BigDecimal relativeAccountBalance, long numberOfTransactions) {
        this.relativeAccountBalance = relativeAccountBalance;
        this.numberOfTransactions = numberOfTransactions;
    }

    public BigDecimal getRelativeAccountBalance() {
        return relativeAccountBalance;
    }

    public long getNumberOfTransactions() {
        return numberOfTransactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyzerOutput that = (AnalyzerOutput) o;
        return Float.compare(that.numberOfTransactions, numberOfTransactions) == 0 &&
                Objects.equals(relativeAccountBalance, that.relativeAccountBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relativeAccountBalance, numberOfTransactions);
    }

    @Override
    public String toString() {
        return "AnalyzerOutput{" +
                "relativeAccountBalance=" + relativeAccountBalance +
                ", numberOfTransactions=" + numberOfTransactions +
                '}';
    }
}
