package net.banking.bankingapplication.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.banking.bankingapplication.dto.AccountDto;
import net.banking.bankingapplication.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	// add account Rest Api
	@PostMapping
	public ResponseEntity<AccountDto> addAccount( @RequestBody AccountDto accountDto){
		
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	//get account rest api
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountbyId( @PathVariable Long id){
		AccountDto accountDto=accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	//deposit rest api
	@PutMapping("/{id}/deposit")
	 public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
		 
		 Double amount=request.get("amount");
		 
		 
		 AccountDto accountDto=accountService.deposit(id,amount);
		 return ResponseEntity.ok(accountDto);
		 
	 }
	
	//withdraw rest Api
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw( @PathVariable Long id,@RequestBody Map<String,Double> request){
	double amount=request.get("amount");
	AccountDto accountDto=accountService.withdraw(id, amount);
	return ResponseEntity.ok(accountDto);
	}
	
	
	//get all accounts
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts=accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	
	//Delete Account 
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount( @PathVariable Long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully");
	}
	
	
	
	
	
	

}
