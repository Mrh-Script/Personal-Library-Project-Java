import java.util.*;
import java.io.*;

public class LibraryManager {
    private Map<String, List<Book>> sectionBooks = new HashMap<>();
    private final String DATA_FILE = "library_data.dat";

    public LibraryManager() {
        sectionBooks.put("Islamic", new ArrayList<>());
        sectionBooks.put("History", new ArrayList<>());
        sectionBooks.put("Others", new ArrayList<>());
        loadFromFile();
    }

    public void addBook(String section, String name, String writer) {
        List<Book> books = sectionBooks.get(section);
        int newId = books.size() + 1;
        books.add(new Book(newId, name, writer, section));
        saveToFile();
    }

    public boolean giveBook(String section, int id, String person) {
        for (Book book : sectionBooks.get(section)) {
            if (book.getId() == id && !book.isGiven()) {
                book.giveTo(person);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String section, int id) {
        for (Book book : sectionBooks.get(section)) {
            if (book.getId() == id && book.isGiven()) {
                book.giveTo(null);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean removeBook(String section, int id) {
        List<Book> books = sectionBooks.get(section);
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                // Reassign IDs
                for (int i = 0; i < books.size(); i++) {
                    books.get(i).setId(i + 1);
                }
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public List<Book> getBooks(String section) {
        return sectionBooks.get(section);
    }

    public int getTotalBooks() {
        int total = 0;
        for (List<Book> list : sectionBooks.values()) {
            total += list.size();
        }
        return total;
    }

    public int getRemainingBooks() {
        int count = 0;
        for (List<Book> list : sectionBooks.values()) {
            for (Book b : list) {
                if (!b.isGiven()) count++;
            }
        }
        return count;
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(sectionBooks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            sectionBooks = (Map<String, List<Book>>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found, starting fresh.");
        }
    }
}