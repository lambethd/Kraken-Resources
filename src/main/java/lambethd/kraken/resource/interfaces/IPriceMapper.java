package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface IPriceMapper {
    float mapToFloat(String price);
}
