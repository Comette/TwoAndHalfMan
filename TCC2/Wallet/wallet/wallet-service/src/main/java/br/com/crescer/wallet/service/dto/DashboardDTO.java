/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.crescer.wallet.service.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author victo
 */
public class DashboardDTO {
    private List<ContractDTO> thisMonthContractDTOs;
    private List<ContractDTO> nextMonthContractDTOs;
    private BigDecimal thisMonthAmountExpense;
    private BigDecimal nextMonthAmountExpense;
    private ContractDTO moreExpensiveContract;

    public DashboardDTO() {
    }

    public BigDecimal getThisMonthAmountExpense() {
        return thisMonthAmountExpense;
    }

    public void setThisMonthAmountExpense(BigDecimal thisMonthAmountExpense) {
        this.thisMonthAmountExpense = thisMonthAmountExpense;
    }

    public BigDecimal getNextMonthAmountExpense() {
        return nextMonthAmountExpense;
    }

    public void setNextMonthAmountExpense(BigDecimal nextMonthAmountExpense) {
        this.nextMonthAmountExpense = nextMonthAmountExpense;
    }

    public List<ContractDTO> getThisMonthContractDTOs() {
        return thisMonthContractDTOs;
    }

    public void setThisMonthContractDTOs(List<ContractDTO> thisMonthContractDTOs) {
        this.thisMonthContractDTOs = thisMonthContractDTOs;
    }

    public List<ContractDTO> getNextMonthContractDTOs() {
        return nextMonthContractDTOs;
    }

    public void setNextMonthContractDTOs(List<ContractDTO> nextMonthContractDTOs) {
        this.nextMonthContractDTOs = nextMonthContractDTOs;
    }

    public ContractDTO getMoreExpensiveContract() {
        return moreExpensiveContract;
    }

    public void setMoreExpensiveContract(ContractDTO moreExpensiveContract) {
        this.moreExpensiveContract = moreExpensiveContract;
    }
    
    
}
