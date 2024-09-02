package net.banking.bankingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.banking.bankingapplication.entity.Account;

public interface Accountrepository extends JpaRepository<Account,Long>{

}
