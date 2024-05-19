package server.dto;

import lombok.*;

/**
 * DTO для получения всей информации для композиции
 */
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BondToPortfolioDTO {

    private Long portfolio_id;
    private Long bond_id;
    private Double bondShare;
    private String portfolioName;
    private String login;

    public Long getPortfolio_id() {
        return portfolio_id;
    }

    public Long getBond_id() {
        return bond_id;
    }

    public Double getBondShare() {
        return bondShare;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public String getLogin() {
        return login;
    }
}