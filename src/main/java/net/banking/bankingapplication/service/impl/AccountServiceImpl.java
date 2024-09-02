package net.banking.bankingapplication.service.impl;
import net.banking.bankingapplication.service.AccountService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.banking.bankingapplication.dto.AccountDto;
import net.banking.bankingapplication.entity.Account;
import net.banking.bankingapplication.mapper.AccountMapper;
import net.banking.bankingapplication.repository.Accountrepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	private Accountrepository accountRepository;
	
	
     
     public AccountServiceImpl(Accountrepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}




	@Override
	 public AccountDto createAccount(AccountDto accountDto) {
		Account account =AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}




	@Override
	public AccountDto getAccountById(Long id) {
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}




	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account=accountRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not exist"));
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}




	@Override
	public AccountDto withdraw(long id, double amount) {
		
		Account account=accountRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not exist"));
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Amount");
			
		}
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}




	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts=accountRepository.findAll();
		 return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
	}




	@Override
	public void deleteAccount(Long id) {
		Account account=accountRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not exist"));
		
		accountRepository.deleteById(id);
		
		
	}

}
