public class Contact {
    private String name;

    public Contact() {
        // Constructeur vide requis pour Firebase
    }

    public Contact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
