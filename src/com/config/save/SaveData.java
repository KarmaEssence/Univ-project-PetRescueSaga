package com.config.save;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Permet de sauvegarder ou recuperer
 * des objets
 * @version 1.0
 */
public class SaveData {

    /**
     * Permet de savoir si le nombre de niveau enregistre
     * et le meme que defini dans LevelGenerator.java
     * @return true s il y a le meme nombre de niveau, false sinon.
     */
    private static boolean compareLevelData(){
        return countFiles("LevelData","Level") == LevelGenerator.getLeveltosave();
    }

    /**
     * Complete le dossier si des niveaux sont manquant.
     * @return renvoie le dossier cree et le rempli si besoin.
     */
    public static File completeFolder(){
        String fileName = selectGoodPath()+"/config/LevelData";
		File levelConfig = new File(fileName);
        if(!levelConfig.exists() || !compareLevelData()){
            LevelGenerator.saveAllLevel();
        }
        return levelConfig;
    }

    /**
     * Compte les fichiers enregistres.
     * @param folder le nom du dossier.
     * @param file le nom du fichier.
     * @return le nombre de fichier dans le
     * repertoire donne.
     */
    public static int countFiles(String folder,String file){
        int index = 1;
        int count = 0;
        String path = selectGoodPath();
        path+= "/config/"+folder+"/"+file+index+".txt";
        File exist = new File(path);
        while(exist.exists()){
            count+=1;
            index+=1;
            path = selectGoodPath();
            path+= "/config/"+folder+"/"+file+index+".txt";
            exist = new File(path);
        }
        return count;
    }

    /**
     * Selectionne le bon debut de path pour
     * que l utilisateur puisse charger les
     * fichiers de sauvegarde
     * @return renvoie le bon path
     * pour l utilisation des configurations.
     */
    private static String selectGoodPath(){
        String path = System.getProperty("user.dir");
        File checkPath = new File(path);
        if(path.endsWith("PetRescueSaga") || hasAGoodChild(checkPath,"src")){
            path+="/src/com";
        }else if(path.endsWith("src") || hasAGoodChild(checkPath,"com")){
            path+="/com";
        }
        return path;
    }

    /**
     * Permet de recuperer des images.
     * @param fileName nom du fichier.
     * @return renvoie l image de fond.
     */
    public static BufferedImage getImageContent(String fileName){
        String path = selectGoodPath();
        BufferedImage image=null;
        try{
            image = ImageIO.read(new File(path+
                    "/display/graphical/ImageContent/"+fileName+".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    /**
     * Verifie que le dossier possede le fichier voulu.
     * @param checkPath dossier.
     * @param wanted nom du fichier recherche.
     * @return true si le dossier contient le fichier.
     * false sinon.
     */
    private static boolean hasAGoodChild(File checkPath,String wanted){
        File[] listOfChildren = checkPath.listFiles();
        if (listOfChildren==null)return false;
        for(File child:listOfChildren){
            if(child.toString().endsWith(wanted)){
                return true;
            }
        }
        return false;
    }

    /**
     * Verifie que le repertoire existe.
     * @param folder le nom du dossier.
     */
    private static void directoryExist(String folder){
        String path = selectGoodPath();
        path+=	"/config/"+folder;
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    /**
     * Verifie que le fichier du niveau existe.
     * @param folder nom du dossier.
     * @param fileName nom du fichier.
     * @throws IOException si le fichier ne peut pas etre cree.
     */
    private static void checkIfFileExist(String folder,String fileName) throws IOException {
        String path = selectGoodPath();
        path+="/config/"+ folder +"/"+fileName;
        File name = new File(path);
        if(name.exists())name.createNewFile();
    }

    /**
     * Enregistre la configuration d un niveau et d un joueur.
     * @param data Objet a enregistrer
     * @param folder nom du dossier.
     * @param file nom du fichier.
     * @param number numero du fichier.
     */
    public static void saveConfig(Object data,String folder,String file,int number){
        String path = selectGoodPath();
        String fileName;
        directoryExist(folder);
        try{
            checkIfFileExist(folder,"Partie"+number);
            fileName = "/config/"+ folder +"/"+file+number+".txt";
            FileOutputStream fos = new FileOutputStream(path+fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Permet de supprimer une fichier de configuration.
     * @param folder nom du dossier.
     * @param file nom du fichier.
     * @param number numero du fichier.
     */
    public static void deleteConfig(String folder,String file,int number){
        String path = selectGoodPath();
        File toDelete = new File(path+ "/config/"+ folder +"/"+file+number+".txt");
        if(toDelete.exists() && toDelete.isFile()){
            toDelete.delete();
        }
    }

    /**
     * Recupere la configuration d un niveau et d un joueur.
     * @param folder nom du dossier.
     * @param fileName nom du fichier.
     * @return configuration du niveau
     */
    public static Object getConfig(String folder, String fileName){
        Object res = null;
        File verifyExist = new File(SaveData.selectGoodPath() +
                "/config/"+ folder +"/"+fileName+".txt");
        if(!verifyExist.exists())return null;
        try{
            FileInputStream fis = new FileInputStream(SaveData.selectGoodPath() +
                    "/config/"+ folder +"/"+fileName+".txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            res = ois.readObject();
            ois.close();

        } catch(ClassNotFoundException | IOException e){
            e.printStackTrace();


        }
        return res;
    }
}
