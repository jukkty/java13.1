    package ru.netology.manager;

    import static org.junit.jupiter.api.Assertions.*;

    import org.junit.jupiter.api.Test;
    import ru.netology.domain.TicketInfo;
    import ru.netology.repository.TicketRepository;
    import ru.netology.domain.NotFoundException;

    import java.util.Arrays;

    class TicketManagerTest {
        private final TicketRepository repository = new TicketRepository();
        private final TicketManager manager = new TicketManager(repository);

        private TicketInfo ticket1 = new TicketInfo(1, 12650, "ARH", "LED", 90);
        private TicketInfo ticket2 = new TicketInfo(2, 9294, "DME", "KUF", 105);
        private TicketInfo ticket3 = new TicketInfo(3, 11162, "DME", "REN", 120);
        private TicketInfo ticket4 = new TicketInfo(4, 10499, "LED", "KZN", 125);
        private TicketInfo ticket5 = new TicketInfo(5, 22182, "MMK", "LED", 110);
        private TicketInfo ticket6 = new TicketInfo(6, 44100, "YKS", "LED", 395);
        private TicketInfo ticket7 = new TicketInfo(7, 9299, "DME", "KUF", 110);

        @Test
        public void ShouldAddAllProducts() {
            manager.add(ticket1);
            manager.add(ticket2);
            manager.add(ticket3);
            manager.add(ticket4);
            manager.add(ticket5);
            manager.add(ticket6);
            TicketInfo[] expected = new TicketInfo[]{ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
            TicketInfo[] actual = repository.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void ShouldRemoveById() {
            manager.add(ticket1);
            manager.add(ticket2);
            manager.add(ticket3);
            manager.add(ticket4);
            manager.add(ticket5);
            manager.add(ticket6);
            manager.removeById(2);
            TicketInfo[] expected = new TicketInfo[]{ticket1, ticket3, ticket4, ticket5, ticket6};
            TicketInfo[] actual = repository.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void FindAllWithFilter() {
            manager.add(ticket1);
            manager.add(ticket2);
            manager.add(ticket3);
            manager.add(ticket4);
            manager.add(ticket5);
            manager.add(ticket6);
            manager.add(ticket7);
            TicketInfo[] expected = new TicketInfo[]{ticket2, ticket7};
            TicketInfo[] actual = manager.findAllWithFilter("DME", "KUF");
            assertArrayEquals(expected, actual);
        }

        @Test
        public void FindAll() {
            manager.add(ticket1);
            manager.add(ticket2);
            manager.add(ticket3);
            manager.add(ticket4);
            manager.add(ticket5);
            manager.add(ticket6);
            manager.add(ticket7);
            TicketInfo[] expected = new TicketInfo[]{ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7};
            TicketInfo[] actual = repository.findAll();
            assertArrayEquals(expected, actual);
        }

        @Test
        public void TicketsSortByPrice() {
            TicketInfo[] expected = new TicketInfo[]{ticket2, ticket4, ticket3, ticket1, ticket5, ticket6};
            TicketInfo[] actual = new TicketInfo[]{ticket2, ticket3, ticket4, ticket1, ticket5, ticket6};
            Arrays.sort(actual);
            assertArrayEquals(expected, actual);
        }

        @Test
        public void ShouldShowNotFoundException() {
            manager.add(ticket1);
            manager.add(ticket2);
            manager.add(ticket3);
            manager.add(ticket4);
            manager.add(ticket5);
            manager.add(ticket6);
            manager.add(ticket7);
            assertThrows(NotFoundException.class, () -> manager.findAllWithFilter("AAA", "BBB"), "По данным направлениям есть предложения");
            System.out.println("NotFoundException is working well");
        }
    }