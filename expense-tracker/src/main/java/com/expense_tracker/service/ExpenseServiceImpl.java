package com.expense_tracker.service;

import com.expense_tracker.dto.ExpenseRequestDTO;
import com.expense_tracker.entity.Expense;
import com.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements  ExpenseService{
    private final ExpenseRepository expenseRepository;


    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense createExpense(ExpenseRequestDTO dto) {
        Expense expense = new Expense();
        expense.setUserId(dto.getUserId());
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setCreatedAt(LocalDateTime.now());

        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);

        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            throw new RuntimeException("Expense not found with id: " + id);
        }
    }

    @Override
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense updateExpense(Long id, ExpenseRequestDTO dto) {
        Expense expense = getExpenseById(id);

        expense.setUserId(dto.getUserId());
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setExpenseDate(dto.getExpenseDate());

        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }
}
