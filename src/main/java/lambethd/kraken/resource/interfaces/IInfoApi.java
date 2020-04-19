package lambethd.kraken.resource.interfaces;


import org.springframework.stereotype.Service;
import runescape.Runeday;

import java.io.IOException;

@Service
public interface IInfoApi {
    Runeday getInfo() throws IOException;
}
