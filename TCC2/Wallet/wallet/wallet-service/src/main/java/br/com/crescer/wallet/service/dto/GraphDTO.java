package br.com.crescer.wallet.service.dto;

import java.util.List;

/**
 *
 * @author Hedo
 */


public class GraphDTO {
    
    private List<ContractDTO> thisMonthContracts;
    private List<ContractDTO> nextMonthContracts;

    public List<ContractDTO> getThisMonthContracts() {
        return thisMonthContracts;
    }

    public void setThisMonthContracts(List<ContractDTO> thisMonthContracts) {
        this.thisMonthContracts = thisMonthContracts;
    }

    public List<ContractDTO> getNextMonthContracts() {
        return nextMonthContracts;
    }

    public void setNextMonthContracts(List<ContractDTO> nextMonthContracts) {
        this.nextMonthContracts = nextMonthContracts;
    }
    
    
    
}
