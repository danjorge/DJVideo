package danielsouza.com.djvideo.entity;

/**
 * Created by daniel.souza on 06/02/2017.
 */

public class MenuItem {

    private String nome;
    private boolean selected;

    public MenuItem(String nome) {
        this.nome = nome;
        this.selected = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
