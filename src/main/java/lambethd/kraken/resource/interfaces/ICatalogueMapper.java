package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;
import runescape.Catalogue;

import java.io.IOException;

@Service
public interface ICatalogueMapper {
    Catalogue mapToCatalogue(String catalogueString) throws IOException;
}
