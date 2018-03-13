package pe.tuna.models;

public class Saludo {
    private final Long id;
    private final String content;

    public Saludo(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
