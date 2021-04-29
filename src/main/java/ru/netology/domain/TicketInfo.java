package ru.netology.domain;

import java.util.Objects;

public class TicketInfo implements Comparable<TicketInfo> {
    private int id;
    private int price;
    private String portDeparture;
    private String portArrival;
    private int time;

    public TicketInfo(int id, int price, String portDeparture, String portArrival, int time) {
        this.id = id;
        this.price = price;
        this.portDeparture = portDeparture;
        this.portArrival = portArrival;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getPortDeparture() {
        return portDeparture;
    }

    public String getPortArrival() {
        return portArrival;
    }

    @Override
    public int compareTo(TicketInfo o) {
        return price - o.price;
    }

    @Override
    public String toString() {
        return "TicketInfo{" +
                "id=" + id +
                ", price=" + price +
                ", portDeparture='" + portDeparture + '\'' +
                ", portArrival='" + portArrival + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketInfo that = (TicketInfo) o;
        return id == that.id && price == that.price && time == that.time && Objects.equals(portDeparture, that.portDeparture) && Objects.equals(portArrival, that.portArrival);
    }
}
