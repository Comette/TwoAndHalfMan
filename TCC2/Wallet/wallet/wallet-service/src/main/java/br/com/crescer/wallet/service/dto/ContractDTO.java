/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.crescer.wallet.service.dto;

import br.com.crescer.wallet.entity.util.Coin;
import br.com.crescer.wallet.entity.util.Periodicity;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author victo
 */

public class ContractDTO {
    private Long id;
    private BigDecimal monthlyExpense;
    private BigDecimal totalCostPercentage;
    private String responsibleUserName;
    
    @NotEmpty
    @Length(max = 255)
    private String name;
    
    @NotEmpty
    @Length(max = 255)
    @Pattern(regexp = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
    private String webSite;
    
    @NotEmpty
    @Length(max = 800)
    private String description;
    
    @NotNull
    private Periodicity periodicity;
    
    @NotNull
    private Coin coin;
  
    @NotNull
    @Range(min = 1)
    private long responsibleUserID;
    
    @NotNull
    @Range(min = 0)
    private BigDecimal amountCost;   

    public ContractDTO() {
    }
    
    public BigDecimal getTotalCostPercentage() {
        return totalCostPercentage;
    }
    
    public void setTotalCostPercentage(BigDecimal amountCost) {
        BigDecimal costPercentage = this.monthlyExpense.multiply(BigDecimal.valueOf(100)).divide(amountCost, 6, HALF_UP);
        this.totalCostPercentage = costPercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonthlyExpense() {
        return monthlyExpense;
    }

    public void setMonthlyExpense(BigDecimal monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
    }

    public String getResponsibleUserName() {
        return responsibleUserName;
    }

    public void setResponsibleUserName(String responsibleUserName) {
        this.responsibleUserName = responsibleUserName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public long getResponsibleUserID() {
        return responsibleUserID;
    }

    public void setResponsibleUserID(long responsibleUserID) {
        this.responsibleUserID = responsibleUserID;
    }

    public BigDecimal getAmountCost() {
        return amountCost;
    }

    public void setAmountCost(BigDecimal amountCost) {
        this.amountCost = amountCost;
    }
    
    
}
