package ru.netology.manager;

import ru.netology.domain.NotFoundException;
import ru.netology.domain.TicketInfo;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

public class TicketManager {
    private TicketRepository repository;

    public TicketManager(TicketRepository repository) {
        this.repository = repository;
    }

    public void add(TicketInfo ticket) {
        repository.save(ticket);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public TicketInfo[] findAllWithFilter(String from, String to) {
        TicketInfo[] result = new TicketInfo[0];
        for (TicketInfo ticket : repository.findAll()) {
            if (matches(ticket, from, to)) {
                TicketInfo[] tmp = new TicketInfo[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        if (result.length == 0)
            throw new NotFoundException("По данным направлениям билетов не найдено");
        Arrays.sort(result);
        return result;
    }

    private boolean matches(TicketInfo ticket, String from, String to) {
        return ticket.getPortDeparture().equalsIgnoreCase(from) & ticket.getPortArrival().equalsIgnoreCase(to);
    }
}
