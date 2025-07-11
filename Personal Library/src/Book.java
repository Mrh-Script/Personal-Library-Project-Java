import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String writer;
    private String section;
    private String givenTo;

    public Book(int id, String name, String writer, String section) {
        this.id = id;
        this.name = name;
        this.writer = writer;
        this.section = section;
        this.givenTo = null;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public String getWriter() { return writer; }
    public String getSection() { return section; }
    public String getGivenTo() { return givenTo; }
    public boolean isGiven() { return givenTo != null; }

    public void giveTo(String person) {
        this.givenTo = person;
    }

    @Override
    public String toString() {
        return "#" + id + " - " + name + " by " + writer + (givenTo != null ? " (Given to: " + givenTo + ")" : "");
    }
}