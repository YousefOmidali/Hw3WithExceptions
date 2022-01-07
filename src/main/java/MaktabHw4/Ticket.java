package MaktabHw4;

import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;

public class Ticket {
    private  String movieName;
    private String cinemaName;
    private Integer ticketId;
    private Date showTime;
    private String reservedBy;

    public Ticket() {
    }

    public Ticket(String movieName, String cinemaName, Integer ticketId, Date showTime, String reservedBy) {
        this.movieName = movieName;
        this.cinemaName = cinemaName;
        this.ticketId = ticketId;
        this.showTime = showTime;
        this.reservedBy = reservedBy;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }
}
