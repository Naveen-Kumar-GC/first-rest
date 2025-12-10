package com.example.demo.service;

import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Loan;
import com.example.demo.repository.LoanRepository;


@Service
public class LoanService {
	
	@Autowired
	LoanRepository repo;
	
	public Loan addLoan(Loan loan) {
		return repo.save(loan);
	}

	public Loan getLoan(Long loanId) {
		Optional<Loan> optionalLoan = repo.findById(loanId);
		return optionalLoan.get();
	}

	public List<Loan> getLoans() {
		return repo.findAll();
	}

	public Loan update(Loan loan) {
		return repo.save(loan);
	}
	
	public Loan update2(Loan loan) {
		Optional<Loan> optional = repo.findById(loan.getLoanId());
		Loan tempLoan = optional.get();
		if(loan.getBorrowerName() != null) {
			tempLoan.setBorrowerName(loan.getBorrowerName());
		}
		if(loan.getDateBorrowed()!=null) {
			tempLoan.setDateBorrowed(loan.getDateBorrowed());
		}
		if(tempLoan.getTennure()!=loan.getTennure()) {
			tempLoan.setTennure(loan.getTennure());
		}
		if(tempLoan.getAmount()<loan.getAmount()) {
			tempLoan.setAmount(loan.getAmount());
		}
		if(tempLoan.isLoanStatus() != loan.isLoanStatus()) {
			tempLoan.setLoanStatus(loan.isLoanStatus());
		}
			
		return repo.save(tempLoan);
	}

	public void remove(Long lid) {
		
		Optional<Loan> loan = repo.findById(lid);
		if(loan.isPresent()) {
			repo.delete(loan.get());
		} else {
			throw new IllegalArgumentException(lid + " not found");
		}
		
	}
	
	
	
}
