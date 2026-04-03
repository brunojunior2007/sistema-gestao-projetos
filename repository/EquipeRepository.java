package repository;

import model.Equipe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EquipeRepository {
    private List<Equipe> banco = new ArrayList<>();

    public void salvarEquipe(Equipe equipe) {
        banco.add(equipe);
    }

    public List<Equipe> listarEquipes() {
        return Collections.unmodifiableList(banco);
    }
}
