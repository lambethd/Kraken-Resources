package lambethd.kraken.resource.dto;

import java.util.Map;

public class WeirdGloopHistoricalDataDto {
    private Map<String, Double[]> values;

    public Map<String, Double[]> getValues() {
        return values;
    }

    public void setValues(Map<String, Double[]> values) {
        this.values = values;
    }
}
