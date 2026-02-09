package com.expense_tracker.service;

import com.expense_tracker.dto.ExpenseRequestDTO;
import com.expense_tracker.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(ExpenseRequestDTO dto);

    Expense getExpenseById(Long id);

    List<Expense> getAllExpense();

    Expense updateExpense(Long id, ExpenseRequestDTO dto);

    void deleteExpense(Long id);
}
