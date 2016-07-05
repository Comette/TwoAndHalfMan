/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.entity.Contract;
import br.com.crescer.wallet.entity.entity.ContractValue;
import br.com.crescer.wallet.entity.entity.Client;
import br.com.crescer.wallet.entity.util.Coin;
import static br.com.crescer.wallet.entity.util.Coin.BRL;
import br.com.crescer.wallet.entity.util.Periodicity;
import static br.com.crescer.wallet.entity.util.State.*;
import br.com.crescer.wallet.service.dto.ContractDTO;
import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.GraphDTO;
import br.com.crescer.wallet.service.repository.ContractRepository;
import static br.com.crescer.wallet.service.service.ServiceUtils.*;
import java.math.BigDecimal;
import static java.math.RoundingMode.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author victo
 */
@Service
public class ContractService {
    
    @Autowired
    ContractRepository contractRepository;
    
    @Autowired
    CurrencyExchangeService currencyExchangeService;
    
    @Autowired
    ClientService userService;
    
    public DashboardDTO generateDashboardData(Pageable pageable) {
        DashboardDTO dashboard = new DashboardDTO();
        {
            List<ContractDTO> thisMonthPagedContractDTOs = this.getThisMonthPagedContractDTOs(pageable);
            dashboard.setThisMonthContractDTOs(thisMonthPagedContractDTOs);
            dashboard.setNextMonthContractDTOs(this.getNextMonthPagedContractDTOs(pageable));
            //TODO: throws exception
            dashboard.setMostExpensiveContract(thisMonthPagedContractDTOs.isEmpty() ? null : thisMonthPagedContractDTOs.get(0));
            dashboard.setThisMonthAmountExpense(this.getThisMonthAmountExpense());
            dashboard.setNextMonthAmountExpense(this.getNextMonthAmountExpense());
        }
        return dashboard;
    }
    
    public BigDecimal getThisMonthAmountExpense() {
        List<ContractDTO> thisMonthContractDTOs = this.getThisMonthContractDTOs(this.getThisMonthContracts());
        BigDecimal thisMonthAmountExpense = this.calculateAmountExpense(thisMonthContractDTOs);
        return thisMonthAmountExpense;
    }
    
    public BigDecimal getNextMonthAmountExpense() {
        List<ContractDTO> nextMonthContractDTOs = this.getNextMonthContractDTOs(this.getNextMonthContracts());
        BigDecimal nextMonthAmountExpense = this.calculateAmountExpense(nextMonthContractDTOs);
        return nextMonthAmountExpense;
    }
    
    public List<ContractDTO> getThisMonthPagedContractDTOs(Pageable pageable) {
        return this.getThisMonthContractDTOs(this.getThisMonthPagedContracts(pageable));
    }
    
    public List<ContractDTO> getNextMonthPagedContractDTOs(Pageable pageable) {
        return this.getNextMonthContractDTOs(this.getNextMonthPagedContracts(pageable));
    }
    
    public List<ContractDTO> getThisMonthPagedAndFilteredByUserContractDTOs(Long idUser, Pageable pageable) {
        return this.getThisMonthContractDTOs(this.getThisMonthPagedAndFilteredByUserContracts(idUser, pageable));
    }
        
    public List<ContractDTO> getNextMonthPagedAndFilteredByUserContractDTOs(Long idUser, Pageable pageable) {
        return this.getNextMonthContractDTOs(this.getNextMonthPagedAndFilteredByUserContracts(idUser, pageable));
    }
    
    public GraphDTO generateGranphData() {
        List<ContractDTO> thisMonthContracts = this.getThisMonthContractDTOs(this.getThisMonthContracts());
        List<ContractDTO> nextMonthContracts = this.getNextMonthContractDTOs(this.getNextMonthContracts());
        {
            //TODO: throws exception
            BigDecimal thisMonthAmoutExpense = this.calculateAmountExpense(thisMonthContracts);
            if (thisMonthAmoutExpense != null) {
                thisMonthContracts.stream().forEach((contract) -> {
                    contract.setTotalCostPercentage(thisMonthAmoutExpense);
                });
            }
            BigDecimal nextMonthAmountExpense = this.calculateAmountExpense(nextMonthContracts);
            //TODO: throws exception
            if (nextMonthAmountExpense != null) {
                nextMonthContracts.stream().forEach((contract) -> {
                    contract.setTotalCostPercentage(nextMonthAmountExpense);
                });
            }
        }
        GraphDTO graphDTO = new GraphDTO();
        {
            graphDTO.setThisMonthContracts(thisMonthContracts);
            graphDTO.setNextMonthContracts(nextMonthContracts);
        }
        return graphDTO;
    }
    
    public ContractDTO getContractDTO(Long idContract) {
        final Map<Coin, BigDecimal> averages = currencyExchangeService.findLastExchangeRate();
        return this.buildDTO(contractRepository.findOne(idContract), averages);
    }
    
    public Contract saveContract(ContractDTO dto) {
        return contractRepository.save(this.buildContract(dto));
    }
        
    public boolean cancelContract(Long idContract){
        try {
            Contract contract = contractRepository.findOne(idContract);
            contract.setDsState(CANCELED);
            contractRepository.save(contract);
            return true;
        } catch (Exception e) {
            //TODO: EXCEPTION 
            return false;
        }
    }
        
    public long countServicosByUsuarioId(Long idClient) {
        return contractRepository.countByClientIdClient_idClientAndDsState(idClient, ACTIVE);
    }
    
    public void cancelContractByIdClient(Long idClient){
        List<Contract> contracts = contractRepository.findByClientIdClient_idClient(idClient);
        contracts.stream().forEach((contract) -> {
            contract.setDsState(CANCELED);
        });        
        contractRepository.save(contracts);
    }    
    
    private List<Contract> getThisMonthContracts() {
        return contractRepository.findByDsStateNot(INACTIVE);
    }
    
    private List<Contract> getNextMonthContracts() {
        return contractRepository.findByDsState(ACTIVE);
    }
    
    private List<Contract> getThisMonthPagedContracts(Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "contractValue.vlMonthlylUSD");
        return contractRepository.findByDsStateNot(INACTIVE, pageable);
    }
    
    private List<Contract> getNextMonthPagedContracts(Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "contractValue.vlMonthlylUSD");
        return contractRepository.findByDsState(ACTIVE, pageable);
    }
    
    private List<Contract> getThisMonthPagedAndFilteredByUserContracts(Long idUser, Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "contractValue.vlMonthlylUSD");
        return contractRepository.findByClientIdClient_idClientAndDsStateNot(idUser, INACTIVE, pageable);
    }
    
    private List<Contract> getNextMonthPagedAndFilteredByUserContracts(Long idUser, Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "contractValue.vlMonthlylUSD");        
        return contractRepository.findByClientIdClient_idClientAndDsState(idUser, ACTIVE, pageable);
    }
    
    private BigDecimal calculateAmountExpense(List<ContractDTO> contractDTOs) {
        BigDecimal amountExpense = BigDecimal.ZERO;
        for (ContractDTO contractDTO : contractDTOs) {
            amountExpense = amountExpense.add(contractDTO.getMonthlyExpense());
        }
        return amountExpense.setScale(CALC_SCALE, HALF_UP);
    }
    
    private List<ContractDTO> getThisMonthContractDTOs(List<Contract> contracts) {
        final Map<Coin, BigDecimal> averages = currencyExchangeService.findLastExchangeRate();
        List<ContractDTO> contractDTO = new ArrayList<>();
        contracts.stream().forEach((contract) -> {
            contractDTO.add(this.buildDTO(contract, averages));
        });
        return contractDTO;
    }
    
    private List<ContractDTO> getNextMonthContractDTOs(List<Contract> contracts) {
        final Map<Coin, BigDecimal> averages = currencyExchangeService.findLastAverage();
        List<ContractDTO> contractDTO = new ArrayList<>();
        contracts.stream().forEach((contract) -> {
            contractDTO.add(this.buildDTO(contract, averages));
        });
        return contractDTO;
    }
    
    private ContractDTO buildDTO(Contract contract, Map<Coin, BigDecimal> averages) {
        //TODO adicionar exception()nullPointer
        if (contract == null) {
            return null;
        }
        BigDecimal monthlyExpense;
        {
            BigDecimal periodicity = BigDecimal.valueOf(contract.getContractValue().getDsPeriodicity().getNumeral());
            BigDecimal average = averages.get(contract.getContractValue().getDsCoin());
            BigDecimal rate = averages.get(BRL);

            monthlyExpense = contract.getContractValue().getVlAmountContractValue()
                    .divide(periodicity, CALC_SCALE, HALF_UP)
                    .divide(average, CALC_SCALE, HALF_UP)
                    .multiply(rate).setScale(PRES_SCALE, HALF_UP);
        }
        ContractDTO contractDTO = new ContractDTO();
        {
            contractDTO.setId(contract.getIdContract());
            contractDTO.setName(contract.getNmContract());
            contractDTO.setWebSite(contract.getDsWebsite());
            contractDTO.setDescription(contract.getDsDescription());
            contractDTO.setPeriodicity(contract.getContractValue().getDsPeriodicity());
            contractDTO.setState(contract.getDsState());
            contractDTO.setCoin(contract.getContractValue().getDsCoin());
            contractDTO.setAmountCost(contract.getContractValue().getVlAmountContractValue());
            contractDTO.setMonthlyExpense(monthlyExpense);
            contractDTO.setResponsibleUserID(contract.getClientIdClient().getIdClient());
            contractDTO.setResponsibleUserName(contract.getClientIdClient().getNmClient());
        }
        return contractDTO;
    }
    
    private Contract buildContract(ContractDTO contractDTO) {
        Contract contract = new Contract();
        {
            contract.setNmContract(contractDTO.getName());
            contract.setDsWebsite(contractDTO.getWebSite());
            contract.setDsDescription(contractDTO.getDescription());
            contract.setDsState(ACTIVE);
        }        
        {
            Client user = userService.findById(contractDTO.getResponsibleUserID());
            contract.setClientIdClient(user);
        }
        {
            ContractValue cValue = this.buildContractValue(contractDTO.getPeriodicity(), contractDTO.getAmountCost(), contractDTO.getCoin());
            contract.setContractValue(cValue);            
        }
        return contract;
    }
    
    private ContractValue buildContractValue(Periodicity periodicity, BigDecimal amount, Coin originalCoin){        
        BigDecimal monthlyExpensesUSD;
        {
            monthlyExpensesUSD = amount
                .divide(BigDecimal.valueOf(periodicity.getNumeral()),CALC_SCALE,HALF_UP)
                .divide(currencyExchangeService.findLastCurrencyAverage(originalCoin),CALC_SCALE,HALF_UP);
        }
        ContractValue cValue = new ContractValue();        
        {
            cValue.setVlAmountContractValue(amount);
            cValue.setVlMonthlylUSD(monthlyExpensesUSD);
            cValue.setDsCoin(originalCoin);
            cValue.setDsPeriodicity(periodicity);
        }            
        return cValue;
    }
    
    @Scheduled(cron = "0 1 0 1 1/1 ?")
    public void updateCancelledServicesStatus() {
        List<Contract> cancelledContracts = contractRepository.findByDsState(CANCELED);
        cancelledContracts.stream().forEach((contract) -> {
            contract.setDsState(INACTIVE);
        });
        contractRepository.save(cancelledContracts);
    }
}
