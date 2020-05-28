package Taquin;

public class ControlGroup {

    private Model model;
    private Fenetre fenetre;
    private ControlButton controlButton;
    private ControlMenu controlMenu;

//------------creation de la fenetre d'ouverture------------
    
    public ControlGroup(Model model) {

        this.model = model;

        fenetre = new Fenetre(model, 4);

        controlMenu = new ControlMenu(fenetre, model);

        fenetre.display();

    }

}
