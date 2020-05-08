package com.newtongroup.library.Entity;

import java.util.List;

public class LibraryCard {

    private long libraryCardId;
    private String libraryCardNumber;
    private boolean isActive;
    private List<Loan> loanList;

    public LibraryCard() {
    }

    public String getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public long getLibraryCardId() {
        return libraryCardId;
    }

    public void setLibraryCardId(long libraryCardId) {
        this.libraryCardId = libraryCardId;
    }

    public void setLibraryCardNumber(String libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }
}
