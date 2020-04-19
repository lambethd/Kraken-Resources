package lambethd.kraken.resource.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lambethd.kraken.resource.interfaces.ICatalogueMapper;
import org.springframework.stereotype.Service;
import runescape.Catalogue;

import java.io.IOException;

@Service
public class CatalogueMapper implements ICatalogueMapper {

    @Override
    public Catalogue mapToCatalogue(String catalogueString) throws IOException {
        return new ObjectMapper().readValue(catalogueString, Catalogue.class);
    }
}
