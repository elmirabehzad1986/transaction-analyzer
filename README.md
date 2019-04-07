# transaction-analyzer

# How to test application
Simply just need to run the following integration test
elmira.transactionanalyser.ApplicationTest::analyse

# Application Design
The main point of the application was to considering reversal transaction while iterating in the time frame transactions. Here I used a HashSet of pre collected reversal transactions in order to have O(1) complicity in the filter condition.

# Improvement Recommendation
For improving the analyse algorithm I would use a TreeSet instead of List to store whole transactions to prevent iterating all the transaction for analysing a specific time-frame.
