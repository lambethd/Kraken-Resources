package lambethd.kraken.resource.dto;

public class BuyingLimitDto {
    String itemName;
    Integer buyingLimit;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getBuyingLimit() {
        return buyingLimit;
    }

    public void setBuyingLimit(Integer buyingLimit) {
        this.buyingLimit = buyingLimit;
    }
}
