package net.banking.bankingapplication.service;

import java.util.List;

import net.banking.bankingapplication.dto.AccountDto;
import net.banking.bankingapplication.entity.Account;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id,double amount);
    AccountDto withdraw(long id,double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
