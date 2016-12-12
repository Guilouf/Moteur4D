import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO ;

import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;

import org.jnativehook.NativeHookException; //permet d'écouter le clavier


/*
 * mettre les axes de facon a ce que haut larg soit tjr sur le bon axe
 * trouver  un truc pour enlever le scintillement de l'ecran
 * mettre des mur bloquant deux dimensions
*/


public class Controleclavier extends Applet {
	

	String merde = "." ;
	char kb;
	Image terre ;
	Image mec ;
	Image pas ;


//    File filePath = new File("").getAbsolutePath();//path relatif?? //donne le mauvais chemin, car va chercher l'executable java.exe et non les sources..
    // problème de variable path? marche pas non plus avec getabsolutefile()
    String filePath  = Controleclavier.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//    c'est moche, mais ca donne l'endoit ou est éxécuté la classe, au moins.

	File img = new File(filePath.concat("terre.jpg"));//path relatif??
	File img2 = new File(filePath.concat("mec.jpg"));// au final, les image avec les sources
    File img3 = new File(filePath.concat("pas.jpg"));



	
	test4D test = new test4D(10) ;

//	test.Lanceur();


	public static void main(String[] args)  {
//		Controleclavier claviertruc = new Controleclavier() ;
//		claviertruc.init();
		System.out.print("\nMain controle_clav");

	}
	
	
	public void init() {

//		test.Lanceur();
        System.out.print("absolutepath: "+filePath);
		try {
			terre = ImageIO.read(img);  //  FIXME: 12/12/2016 gros problème d'images..
            mec = ImageIO.read(img2);
			pas = ImageIO.read(img3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new HandleKeyboard() );
        System.out.print("\nInit controle_clav");
			
	}
	
	class HandleKeyboard extends KeyAdapter {
    	public void keyPressed(KeyEvent k) {
    		Graphics g = getGraphics();
    		kb = k.getKeyChar() ;
    		test.moovegroove(kb);
    		merde = test.stringmerde() ;
    		update(g);  
    	}
    }
	
	
	
	public void paint(Graphics g)  {
		
		int [] vue = test.vue ;
		int dim1 = test.dim1 ;
		int dim2 = test.dim2 ;
		int dim3 = test.dim3 ;
		int dim4 = test.dim4 ;
		int[]posi = test.posi ;
		
		int daa = 40;
		
		for (int da = 0 ; da < test.taille ; da++) {
			int dbb = 40 ;
			daa +=  25 ;
			for (int db = 0 ; db < test.taille ; db++) {
				
				dbb += 25 ;
				
				int dimA = vue[0] ;
				int dimB = vue[1] ;
				
				switch (dimA) {
					case 1: dim1 = da ;	
					break;
					case 2: dim2 = da ;	
					break;
					case 3: dim3 = da ;
					break;
					case 4: dim4 = da ;
					break;					
				}				
				
				switch (dimB) {
					case 1: dim1 = db ;	
					break;
					case 2: dim2 = db ;	
					break;
					case 3: dim3 = db ;	
					break;
					case 4: dim4 = db ;
					break;					
				}
			
				for (int i = 0 ; i < 4 ; i++ ) { // FIXME: 12/12/2016 c'est hardcodé
					if ( i == dimA-1 || i == dimB-1 ) {
						// et alors quoi?
					}
					
					else
						switch(i) {
						case 0: dim1 = posi[0] ;
						break;
						case 1: dim2 = posi[1] ;
						break;
						case 2: dim3 = posi[2] ;
						break;
						case 3: dim4 = posi[3] ;
						}
				}
					
				int point = test.carte[dim1][dim2][dim3][dim4]  ;
								
				switch (point) {
					case 1: g.drawImage(terre, dbb, daa, this) ;
					break;
					case 0:  g.drawImage(pas, dbb, daa, this);
					break;
					case 10:  g.drawImage(mec, dbb, daa, this) ;
					break;
				}
				
				g.drawString("Position Bonhomme: Haut: "+posi[0]+" Large: "+posi[1]+" Prof: "+posi[2]+" 4th: "+posi[3]+"", 20, 15);
				//g.drawString(Arrays.toString(test.vue), 20, 30);
				
				switch(Arrays.toString(test.vue)) {
				case ("[1, 2]"): 
				g.drawString(" Larg ", 160, 50);
				g.drawString(" Haut ", 20, 160);
				break;
				case ("[1, 3]"): 
				g.drawString(" Prof ", 160, 50);
				g.drawString(" Haut ", 20, 160);
				break;
				case ("[1, 4]"): 
				g.drawString(" 4th ", 160, 50);
				g.drawString(" Haut ", 20, 160);
				break;
				case ("[2, 3]"): 
				g.drawString(" Prof ", 160, 50);
				g.drawString(" Larg ", 20, 160);
				break;
				case ("[2, 4]"): 
				g.drawString(" 4th ", 160, 50);
				g.drawString(" Larg ", 20, 160);
				break;
				case ("[3, 4]"): 
				g.drawString(" 4th ", 160, 50);
				g.drawString(" Prof ", 20, 160);
				break;
				
				}
			}
		}
	}
}
