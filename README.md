# Transaction


                                                       #Transaction Management
What are ACID Properties?
Atomicity
All changes to data are performed as if they are a single operation. That is, all the changes are performed, or none of them are.
For example, in an application that transfers funds from one account to another, the atomicity property ensures that, if a debit is made successfully from one account, the corresponding credit is made to the other account.
Consistency
Data is in a consistent state when a transaction starts and when it ends.
For example, in an application that transfers funds from one account to another, the consistency property ensures that the total value of funds in both the accounts is the same at the start and end of each transaction.
Isolation
The intermediate state of a transaction is invisible to other transactions. As a result, transactions that run concurrently appear to be serialized.
For example, in an application that transfers funds from one account to another, the isolation property ensures that another transaction sees the transferred funds in one account or the other, but not in both, nor in neither.
Durability
After a transaction successfully completes, changes to data persist and are not undone, even in the event of a system failure.
For example, in an application that transfers funds from one account to another, the durability property ensures that the changes made to each account will not be reversed.

REQUIRED behavior
Spring REQUIRED behavior means that the same transaction will be used if there is an already opened transaction in the current bean method execution context. 
If there is no existing transaction the Spring container will create a new one. 
If multiple methods configured as REQUIRED behavior are called in a nested way they will be assigned distinct logical transactions but they will all share the same physical transaction. In short this means that if an inner method causes a transaction to rollback, the outer method will fail to commit and will also rollback the transaction.

Note that the inner method throws a RuntimeException and is annotated with REQUIRED behavior. This means that it will use the same transaction as the outer bean, so the outer transaction will fail to commit and will also rollback.


REQUIRES_NEW behaviour :-
REQUIRES_NEW behavior means that a new physical transaction will always be created by the container.

 In other words the inner transaction may commit or rollback independently of the outer transaction, i.e. the outer transaction will not be affected by the inner transaction result: they will run in distinct physical transactions.
The inner method is annotated with REQUIRES_NEW and throws a RuntimeException so it will set its transaction to rollback but will not affect the outer transaction. 
The outer transaction is paused when the inner transaction starts and then resumes after the inner transaction is concluded. They run independently of each other so the outer transaction may commit successfully.

MANDATORY behaviour:-
The MANDATORY behavior states that an existing opened transaction must already exist. If not an exception will be thrown by the container.

NEVER behaviour:-
The NEVER behavior states that an existing opened transaction must not already exist. If a transaction exists an exception will be thrown by the container.
NOT_SUPPORTED behaviour:-
The NOT_SUPPORTED behavior will execute outside of the scope of any transaction. If an opened transaction already exists it will be paused.
SUPPORTS behaviour:-
The SUPPORTS behavior will execute in the scope of a transaction if an opened transaction already exists. If there isn't an already opened transaction the method will execute anyway but in a non-transactional way.

