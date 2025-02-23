package TaskSorter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {
    private String titulo;
    private String descricao;
    private String prioridade;
    private String categoria;
    private boolean concluida;
    private LocalDate prazo;

    public Task(String titulo, String descricao, String prioridade, String categoria, String prazo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.concluida = false;
        this.prazo = LocalDate.parse(prazo, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getPrioridade() { return prioridade; }
    public String getCategoria() { return categoria; }
    public boolean isConcluida() { return concluida; }
    public LocalDate getPrazo() { return prazo; }

    public void concluir() { this.concluida = true; }

    public boolean estaAtrasada() {
        return !concluida && LocalDate.now().isAfter(prazo);
    }

    @Override
    public String toString() {
        return (concluida ? "[✔]" : "[ ]") + " " + titulo + " - " + prioridade +
                " - " + categoria + " (Vence: " + prazo + ")";
    }
}
