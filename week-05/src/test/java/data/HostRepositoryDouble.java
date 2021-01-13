package data;

import models.Host;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HostRepositoryDouble implements HostRepository {
    public final static Host HOST = makeHost();

    private final ArrayList<Host> hosts = new ArrayList<>();

    public HostRepositoryDouble() {
        hosts.add(HOST);
    }


    @Override
    public List<Host> findAll() {
        return hosts;
    }

    @Override
    public Host findByEmail(String hostEmail) {
        return hosts.stream()
                .filter(i -> i.getHostEmail().equals(hostEmail))
                .findFirst()
                .orElse(null);
    }

    public static Host makeHost() {
        Host host = new Host();

        host.setHostId("3edda6bc-ab95-49a8-8962-d50b53f84b15");
        host.setLastName("Yearnes");
        host.setHostEmail("eyearnes0@sfgate.com");
        host.setPhoneNumber("(806) 1783815");
        host.setAddress("3 Nova Trail");
        host.setCity("Amarillo");
        host.setState("TX");
        host.setPostalCode(79182);
        host.setStandardRate(BigDecimal.valueOf(340));
        host.setWeekendRate(BigDecimal.valueOf(425));

        return host;
    }
}
