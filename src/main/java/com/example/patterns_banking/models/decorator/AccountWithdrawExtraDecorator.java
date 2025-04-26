package com.example.patterns_banking.models.decorator;


import com.example.patterns_banking.models.Account;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class AccountWithdrawExtraDecorator extends AccountDecorator {
    public AccountWithdrawExtraDecorator(Account account) {
        super(account);
    }

    @Override
    public void withdraw(double amount) {
        double limit = calculateLimitWithdrawal();
        double balance = getBalance();
        double fee = calculateWithdrawalFee(amount);
        double totalAmount = amount + fee;

        if (totalAmount > balance) {
            double remainingAmount = totalAmount - balance;
            System.out.println("El saldo actual no es suficiente. Se usará el límite extra.");
            System.out.println("Excedente adicional necesario para el retiro: " + remainingAmount);
            if (remainingAmount > limit) {
                System.out.println("El monto excede el límite extra permitido por: " + (remainingAmount - limit));
                throw new IllegalArgumentException("El monto excede el límite extra permitido.");
            }
            setBalance(0.0);
            limit -= remainingAmount;
            System.out.println("Se utilizó del límite extra: " + remainingAmount);
        } else {
            setBalance(balance - totalAmount);
        }
        System.out.println("Se ha realizado un retiro de: " + amount + " con un fee de: " + fee);
        System.out.println("Saldo actual: " + getBalance() + ", Límite extra restante: " + limit);
    }
}
