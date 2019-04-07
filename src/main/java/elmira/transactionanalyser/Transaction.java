package elmira.transactionanalyser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private String transactionId;

    private String relatedTransaction;
    private String fromAccountId;
    private String toAccountId;
    private LocalDateTime createAt;
    private BigDecimal amount;
    private TransactionType transactionType;

    public Transaction(String transactionId, String fromAccountId, String toAccountId, LocalDateTime createAt, BigDecimal amount, TransactionType transactionType, String relatedTransaction) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.createAt = createAt;
        this.amount = amount;
        this.transactionType = transactionType;
        this.relatedTransaction = relatedTransaction;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(fromAccountId, that.fromAccountId) &&
                Objects.equals(toAccountId, that.toAccountId) &&
                Objects.equals(createAt, that.createAt) &&
                Objects.equals(amount, that.amount) &&
                transactionType == that.transactionType &&
                Objects.equals(relatedTransaction, that.relatedTransaction);

    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, fromAccountId, toAccountId, createAt, amount, transactionType, relatedTransaction);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", fromAccountId='" + fromAccountId + '\'' +
                ", toAccountId='" + toAccountId + '\'' +
                ", createAt=" + createAt +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", relatedTransaction=" + relatedTransaction +
                '}';
    }
}
