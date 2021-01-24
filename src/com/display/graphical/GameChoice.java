package com.display.graphical;

import com.config.save.PlayerConfig;
import com.config.save.SaveData;
import com.user.human.GraphicalHumanInteract;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Panneau de selection de la sauvegarde.
 * @version 1.0
 */
public class GameChoice extends JPanel{
    private final GraphicalHumanInteract graphInteract;
    private final PlayerConfig[] saveOfSomething;
    private final JPanel titlePanel;
    private final JPanel contentPanel;
    private final JPanel actionPanel;
    private JButton[] createButton;
    private JTextArea[] userNameField;
    private final BufferedImage image;

    /**
     * Construit la page de selection de la sauvegarde.
     * @param graphInteract le choix de l utilisateur.
     * @param saveOfSomething les emplacements de sauvegarde.
     */
    public GameChoice(GraphicalHumanInteract graphInteract,
                      PlayerConfig[] saveOfSomething){

        image= SaveData.getImageContent("Main");
        this.graphInteract = graphInteract;
        this.saveOfSomething = saveOfSomething;
        this.setLayout(new GridLayout(3,2));
        titlePanel = new JPanel();
        contentPanel = new JPanel();
        actionPanel = new JPanel();

        userNameField = new JTextArea[3];
        titlePanel.setOpaque(false);
        contentPanel.setOpaque(false);
        actionPanel.setOpaque(false);
        contentPanel.setLayout(new GridLayout(1,saveOfSomething.length));


        fillInTitlePanel();
        fillInContentPanel();
        fillInActionPanel();

        add(titlePanel);
        add(contentPanel);
        add(actionPanel);
    }

    /**
     * Met a jour l image de fond.
     * @param g composant graphique.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Met en place le titre de la page.
     */
    private void fillInTitlePanel(){
        JLabel title = new JLabel("<html><B>Choice of Game</B></html>");
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setForeground(Color.ORANGE);
        titlePanel.add(title);
    }

    /**
     * Met en place l affichage des
     * des sauvegardes disponible.
     */
    private void fillInContentPanel(){
        for(int i=0;i<saveOfSomething.length;i++){
            JLabel line;
            String content;
            boolean notCreated = false;
            if (saveOfSomething[i] == null) {
                content = "New Game";
                notCreated= true;
            } else {
                content = saveOfSomething[i].toString();
            }
            line = new JLabel((i + 1) + ". " + content);
            applyFilter(line,i,notCreated);
        }
    }

    /**
     * Met en place les boutons pour choisir la partie.
     */
    private void fillInActionPanel(){
        createButton = new JButton[saveOfSomething.length];

        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.LINE_AXIS));
        for(int i=0;i<saveOfSomething.length;i++){
            if (saveOfSomething[i] != null) {
                JButton game = new JButton("Choose this");
                JButton delete = new JButton("Delete");
                delete.setSize(25, 25);
                game.setSize(25, 25);
                int finalI = i + 1;
                game.addActionListener((event) -> graphInteract.setGameChoice("c" + finalI));
                delete.addActionListener((event) -> graphInteract.setGameChoice("d" + finalI));
                actionPanel.add(Box.createHorizontalGlue());
                actionPanel.add(game);
                actionPanel.add(Box.createRigidArea(new Dimension(5, 10)));
                actionPanel.add(delete);
                actionPanel.add(Box.createHorizontalGlue());
            }else{
                actionPanel.add(Box.createHorizontalGlue());
                actionPanel.add(Box.createRigidArea(new Dimension(20,10)));
                createButton[i] = new JButton("Create new Save");
                createButton[i].setEnabled(false);
                int finalI = i + 1;
                int finalI1 = i;
                createButton[i].addActionListener((event) -> {
                    if(checkArg(userNameField[finalI1].getText())) {
                        graphInteract.setGameChoice("c" + finalI);
                    }else{
                        createButton[finalI1].setEnabled(false);
                        printAnError("Wrong username","You must choose an another username");
                        userNameField[finalI1].setText("");
                    }
                });
                actionPanel.add(createButton[i]);
                actionPanel.add(Box.createRigidArea(new Dimension(20,10)));
                actionPanel.add(Box.createHorizontalGlue());
            }
        }

    }

    /**
     * Modifie l apparence des JLabel.
     * @param line ligne courante.
     * @param currentIndex indice de la sauvegarde.
     * @param notCreated si l emplacement de sauvegarde n existe
     *                   pas.
     */
    private void applyFilter(JLabel line,int currentIndex,boolean notCreated){
        if(line!=null){
            line.setFont(new Font("Serif", Font.PLAIN, 18));
            line.setForeground(Color.WHITE);
            line.setHorizontalAlignment(SwingConstants.CENTER);
            fillSubContentPanel(currentIndex,notCreated);
        }
    }

    /**
     * Remplie le panneau content.
     * @param currentIndex indice de la sauvegarde courante.
     * @param notCreated si l emplacement de sauvegarde n existe
     *                   pas.
     */
    private void fillSubContentPanel(int currentIndex,boolean notCreated){
        JPanel config = new JPanel();
        config.setOpaque(false);
        config.setLayout(new GridLayout(4,1));
        if(!notCreated){
            alreadyCreated(config,currentIndex);
        }else{
            notCreated(config,currentIndex);
        }
        contentPanel.add(config);
    }

    /**
     * Remplie le panneau config avec une description d une sauvegarde
     * existante.
     * @param config un sous panneau contenant la configuration d un niveau.
     * @param currentIndex indice de la sauvegarde choisie.
     */
    private void alreadyCreated(JPanel config,int currentIndex){
        for(int i=0;i<3;i++){
            String res=takeLinePerLine(i,currentIndex);
            JLabel line=new JLabel(res);
            line.setForeground(Color.BLACK);
            line.setHorizontalAlignment(SwingConstants.CENTER);
            config.add(line);
        }
    }

    /**
     * Remplie le panneau config avec une description vide
     * car la sauvegarde n existe pas.
     * @param config un sous panneau contenant la configuration d un niveau.
     * @param currentIndex indice de la sauvegarde choisie.
     */
    private void notCreated(JPanel config,int currentIndex){
        config.setLayout(new BoxLayout(config,BoxLayout.Y_AXIS));
        config.add(Box.createVerticalGlue());
        JPanel menuUser = new JPanel();
        JPanel menuTypeOfPlayer = new JPanel();
        menuUser.setOpaque(false);
        menuTypeOfPlayer.setOpaque(false);

        userNameField[currentIndex] = new JTextArea();
        JLabel userName = new JLabel("UserName:");
        userName.setForeground(Color.BLACK);
        JButton human = new JButton("Human");
        human.addActionListener((event) -> {
            createButton[currentIndex].setEnabled(true);
            graphInteract.setPlayerData(0, userNameField[currentIndex].getText());
            graphInteract.setPlayerData(1, "h");

        });
        JButton robot = new JButton("Robot");
        robot.addActionListener((event) -> {
            createButton[currentIndex].setEnabled(true);
            graphInteract.setPlayerData(0, userNameField[currentIndex].getText());
            graphInteract.setPlayerData(1, "r");
        });

        userNameField[currentIndex].setPreferredSize(new Dimension(100, 20));

        userName.setLabelFor(userNameField[currentIndex]);
        menuUser.add(userName);
        menuUser.add(userNameField[currentIndex]);
        menuTypeOfPlayer.add(human);
        menuTypeOfPlayer.add(robot);
        config.add(menuUser);
        config.add(Box.createRigidArea(new Dimension(10,50)));
        config.add(menuTypeOfPlayer);
    }

    /**
     * Verification du format de la reponse entre par le joueur.
     * @param s une chaine de caractere.
     * @return true si la chaine de caractere
     * respecte le format,false sinon.
     */
    public boolean checkArg(String s){
        if(s.length()==0)return false;
        for(int i=0;i<s.length();i++) {
            if(!(s.charAt(i) >= 49 && s.charAt(i) <= 57)
                    && !(s.charAt(i) >= 65 && s.charAt(i) <= 90)
                    && !(s.charAt(i) >= 97 && s.charAt(i) <= 122)){
                return false;
            }
        }
        return true;
    }

    /**
     * Affiche une erreur
     * @param title titre de l erreur.
     * @param message message d erreur.
     */
    public void printAnError(String title,String message){
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Renvoie une description d une ligne
     * de la sauvegarde.
     * @param index indice de la ligne choisit.
     * @param currentIndex indice de la sauvegarde choisie.
     * @return une description de la ligne a l indice donne.
     */
    private String takeLinePerLine(int index,int currentIndex){
        String res="";
        switch (index){
            case 0:res = "UserName: "+saveOfSomething[currentIndex].getPlayerName();break;
            case 1:res = "HighScores: "+saveOfSomething[currentIndex].countTotalScore();break;
            case 2:res = "NumberOfStars: "+saveOfSomething[currentIndex].countStars();break;
        }
        return res;
    }
}
