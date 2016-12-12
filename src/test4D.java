import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;




public class test4D {
	
	
	public int [][][][] carte ;
	public int taille ;
	public int dim1 ;
	public int dim2 ;
	public int dim3 ;
	public int dim4 ;
	
	public int [] posi ;
	public int [] vue ;

	
	
	
	
	//MAIN/////////////////////////////////////////////////////////
	public static void main(String[] args) {
		// jessaye de relancer le truc..
		//this.Lanceur();
//		test4D test = new test4D(10) ; //nouveau..
        System.out.print("Main de test4D");
	

	}

	
	public test4D Lanceur()  {
		test4D testruc = new test4D(10) ;
        System.out.print("\nLanceur de test4D");
		
		
		return testruc ;
		
	}
	
	
	//CONSTRUCTEUR////////////////////////////////////////////////////
	
	public test4D(int taille) {
		
		posi = new int[4] ;
		vue = new int[2] ;
		
		this.taille = taille ;
		this.carte = new int[taille][taille][taille][taille] ;
		
		for (int d1 = 0 ; d1 < taille ; d1++) {
			for (int d2 = 0 ; d2 < taille ; d2++) {
				for (int d3 = 0 ; d3 < taille ; d3++) {
					for (int d4 = 0 ; d4 < taille ; d4++) {
						carte[d1][d2][d3][d4] = 1 ;
					}
				}
			}
		}
		modify(1, 1, 5, 5, 10);//tracé bonhomme
		setPosi(1,1,5,5) ;//position bonhomme
		setView(1,2) ;
	}
	
	//MODIFIEURS///////////////////////////////////////////////////
	public void modify(int a , int b, int c, int d, int val) {
		carte[a][b][c][d] = val ;
		
	}
	
	public void modify(int [] posi, int val) {//surchage pour placer bonhomme
		int a = posi[0] ;
		int b = posi[1] ;
		int c = posi[2] ;
		int d = posi[3] ;
		
		carte[a][b][c][d] = val ;
				
	}
	
	public void modifyView(int a , int b, int c, int d ) {
		this.dim1 = a ;
		this.dim2 = b ;
		this.dim3 = c ;
		this.dim4 = d ;
	}
	
	//LOAD SAVE///////////////////////////////////////////////////////////
	public void save() throws IOException {
		FileWriter savemap = new FileWriter("Carte.csv") ;
		
		for (int d1 = 0 ; d1 < taille ; d1++) {
			for (int d2 = 0 ; d2 < taille ; d2++) {
				for (int d3 = 0 ; d3 < taille ; d3++) {
					for (int d4 = 0 ; d4 < taille ; d4++) {
						
						String point = Integer.toString(carte[d1][d2][d3][d4]) ;
						
						savemap.append(""+point+";") ;
						savemap.append(""+d1+";") ;
						savemap.append(""+d2+";") ;
						savemap.append(""+d3+";") ;
						savemap.append(""+d4+";") ;
						savemap.append("\n") ;
					}
				}
			}
		}
	
		
		savemap.close();
	}
	
	public void load()  throws IOException {///////////////////////metre try catch!!!!!!!!!!!!
		
		FileReader savemap = new FileReader("Carte.csv") ;
		BufferedReader br = new BufferedReader(savemap); 
		
		String s; 
		
		while((s = br.readLine()) != null) { 
			String[] tab = s.split(";");
			int val = Integer.parseInt(tab[0]) ;
			int d1 = Integer.parseInt(tab[1]) ;
			int d2 = Integer.parseInt(tab[2]) ;
			int d3 = Integer.parseInt(tab[3]) ;
			int d4 = Integer.parseInt(tab[4]) ;
			carte[d1][d2][d3][d4] = val ;
		} 
			
		
		savemap.close();
	}
	
	//AFFICHAGE/////////////////////////////////////////////////////////////////////////
	public void visuel(int[] vue, int[] posibonh ) { 
		
		int dimA = vue[0] ;
		int dimB = vue[1] ;
	
		//parcourt la carte selon l'axe choisit,j avec des valeurs statiques pour les autres.
		for (int da = 0 ; da < taille ; da++) {
			for (int db = 0 ; db < taille ; db++) {
								
				//choisit les axes. pb du switch case.	=>break!				
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
				
				//centre la vue sur les dim non affichées du bonhomme
				
				for (int i = 0 ; i < 4 ; i++ ) {
					if ( i == dimA-1 || i == dimB-1 ) {
						
					}
					
					else
						switch(i) {
						case 0: dim1 = posibonh[0] ;
						break;
						case 1: dim2 = posibonh[1] ;
						break;
						case 2: dim3 = posibonh[2] ;
						break;
						case 3: dim4 = posibonh[3] ;
						}
				}
						
				int point = carte[dim1][dim2][dim3][dim4]  ;
								
				switch (point) {
					case 1: System.out.print("X ");
					break;
					case 0: System.out.print("O ");
					break;
					case 10: System.out.print("8 ");
					break;
				}			
				
			}
			
			System.out.print("\n");
		}	
		System.out.print("\n postion dimension \n") ;
		System.out.print(dim1) ;
		System.out.print(dim2) ;
		System.out.print(dim3) ;
		System.out.print(dim4) ;
		System.out.print(' ') ;
		System.out.print(dimA) ;
		System.out.print(dimB) ;
		System.out.print("\n") ;
	}
	
	//SET GET PRINT
	public void setPosi(int a,int b,int c,int d) {
		posi[0] = a ;
		posi[1] = b ;
		posi[2] = c ;
		posi[3] = d ;
	}
	
	public int[] getPosi() {
		return posi ;
	}
	
	public String printPosi() {
		System.out.print("Position bonhomme\n") ;
		for (int i=0 ; i<4 ; i++) {
			String prposi = Integer.toString(posi[i]) ;
			System.out.print(prposi) ;
		}
		System.out.print("\n") ;
		return "" ;
	}
	
	public String stringmerde() {
		String merde = "merde"+posi[0]+"merde" ;
		return merde ;
	}
	
	public void setView(int vue1, int vue2) {
		vue[0] = vue1 ;
		vue[1] = vue2 ;
	}
	
	public int getTaille() {
		return taille ;
	}
	
	
	//MOUVEMENT
	public void moovegroove(char key) {
        /**
         *
         */
        //System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		System.out.println("\n") ;

        if (key == 'P')  {
        	System.out.println("MERDE") ; // FIXME: marche pas...
        }
        
        switch(key) {
        case '1': setView(1,2) ;
        visuel(vue, posi) ;
        break;
        case '2': setView(1,3) ;
        visuel(vue, posi) ;
        break;
        case '3' : setView(1,4) ;
        visuel(vue, posi) ;
        break;
        case '4': setView(2,3) ;
        visuel(vue, posi) ;
        break;
        case '5': setView(2,4) ;
        visuel(vue, posi) ;
        break;
        case '6': setView(3,4) ;
        visuel(vue, posi) ;
        break;
        
        case 'z' : 
        	modify(posi , 0);
        	if (posi[0] > 0 && posi[0] <= getTaille()-1 ) {
        		posi[0] += -1 ;
        	}	
			modify(posi , 10);
        	System.out.println("\n") ;
        	visuel(vue, posi) ;
        	break;
            
        case 's' :
        	modify(posi , 0);
        	if (posi[0] >= 0 && posi[0] < getTaille()-1 ) {
        		posi[0] += 1 ;
        	}	
			modify(posi , 10);
        	System.out.println("\n") ;
        	visuel(vue, posi) ;
        	 break;
        case 'd' :
        	modify(posi , 0);
        	if (posi[1] >= 0 && posi[1] < getTaille()-1 ) {
        		posi[1] += 1 ;
        	}	
			modify(posi , 10);
        	System.out.println("\n") ;
        	visuel(vue, posi) ;
        	 break;
        case 'q' :
        	modify(posi , 0);
        	if (posi[1] > 0 && posi[1] <= getTaille()-1 ) {
        		posi[1] += -1 ;
        	}	
			modify(posi , 10);
        	System.out.println("\n") ;
        	visuel(vue, posi) ;
        	 break;
        
        case 'a' :
        	modify(posi , 0);
        	if (posi[3] > 0 && posi[3] <= getTaille()-1 ) {
        		posi[3] += -1 ;
        	}	
			modify(posi , 10);
        	System.out.println("\n") ;
        	visuel(vue, posi) ;
        	 break;
        	 
        case 'e' :
        	modify(posi , 0);
        	if (posi[3] >= 0 && posi[3] < getTaille()-1 ) {
        		posi[3] += 1 ;
        	}	
			modify(posi , 10);
        	System.out.println("\n") ;
        	visuel(vue, posi) ;
        	 break;
        	 
        case 'r' :
        	modify(posi , 0);
        	if (posi[2] > 0 && posi[2] <= getTaille()-1 ) {
        		posi[2] += -1 ;
        	}	
			modify(posi , 10);
        	System.out.println("\n") ;
        	visuel(vue, posi) ;
        	 break;
        	 
        case 'f' :
        	modify(posi , 0);
        	if (posi[2] >= 0 && posi[2] < getTaille()-1 ) {
        		posi[2] += 1 ;
        	}	
			modify(posi , 10);
        	System.out.println("\n") ;
        	visuel(vue, posi) ;
        	 break;
        }
        
        
        printPosi() ;
	}

	
}
