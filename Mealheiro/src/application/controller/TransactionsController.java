package application.controller;

import application.command.CommandHistory;
import application.command.addTransactionCommand;
import application.model.Transaction;
import application.model.UserList;
import application.view.TransactionsView;
import java.awt.event.ActionEvent;

/**
 *
 * @author ed
 */
public class TransactionsController extends AbstractController {

    private CommandHistory ch;
    private UserList db;
    private TransactionsView tv;
    private Transaction originalTransaction;
    private Transaction inverseOriginal;

    public TransactionsController(UserList model, TransactionsView tv) {
        this.db = model;
        this.tv = tv;
        this.ch = new CommandHistory();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            System.out.println("Transactions controller: submit button clicked");
            if (db.getLoggedInUser() != null) {
                if ((tv.getCbTransactionSourceAccount() != tv.getCbTransactionDestinationAccount()) && tv.getFtfTransactionAmount() != null) {
                    // instantiate original transaction
                    originalTransaction = new Transaction(tv.getFtfTransactionAmount(), tv.getCbTransactionType(), tv.getCbTransactionSourceAccount(), tv.getCbTransactionDestinationAccount(), tv.getTfTransactionDescription(), tv.getTfTransactionCategory(), tv.getFtfTransactionDate());
                    // instantiate the inverse of the original transaction (switch accounts)
                    inverseOriginal = new Transaction(tv.getFtfTransactionAmount(), tv.getCbTransactionType(), tv.getCbTransactionDestinationAccount(), tv.getCbTransactionSourceAccount(), tv.getTfTransactionDescription(), tv.getTfTransactionCategory(), tv.getFtfTransactionDate());
                    // add to command history undo stack
                    ch.addToUndoStack(new addTransactionCommand(db.getLoggedInUser(), inverseOriginal));
                    // add transaction to user, also executes the transaction
                    db.getLoggedInUser().addTransaction(originalTransaction);
                    // set undo and redo buttons active or not
                    setUndoRedoButtons();
                    // set transaction information label
                    tv.setLblTransactionInformation("Transaction completed!");
                } else {
                    tv.setLblTransactionInformation("Amount can't be empty and accounts can't be the same.");
                }
            }
        }

        if (e.getActionCommand().equals("Undo")) {
            System.out.println("Transactions controller: undo button clicked");
            if (db.getLoggedInUser() != null) {
                // add transaction to command history redo stack
                ch.addToRedoStack(new addTransactionCommand(db.getLoggedInUser(), originalTransaction));
                // delete old transaction
                db.getLoggedInUser().getTransactions().remove(originalTransaction);
                // execute transactions
                ch.undo();
                // set undo and redo buttons active or not
                setUndoRedoButtons();
                // set transaction information label
                tv.setLblTransactionInformation("Transaction undone...");
            } else {
                tv.setLblTransactionInformation("Can't undo.");
            }
        }

        if (e.getActionCommand().equals("Redo")) {
            System.out.println("Transactions controller: redo button clicked");
            if (db.getLoggedInUser() != null) {
                // add transaction to command history undo stack
                ch.addToUndoStack(new addTransactionCommand(db.getLoggedInUser(), inverseOriginal));
                // delete th
                db.getLoggedInUser().getTransactions().remove(inverseOriginal);
                // execute transactions
                ch.redo();
                // set undo and redo buttons active or not
                setUndoRedoButtons();
                // set transaction information label
                tv.setLblTransactionInformation("Transaction redone...");
            } else {
                tv.setLblTransactionInformation("Can't redo.");
            }
        }

        super.actionPerformed(e);
    }

    public void setUndoRedoButtons() {
        // is undo stack empty, return true if its empty, false if contains any item
        if (!ch.isUndoEmpty()) {
            tv.setUndoButtonEnabled(true);
        } else {
            tv.setUndoButtonEnabled(false);
        }
        // is redo stack empty, return true if its empty, false if contains any item
        if (!ch.isRedoEmpty()) {
            tv.setRedoButtonEnabled(true);
        } else {
            tv.setRedoButtonEnabled(false);
        }
    }

}
