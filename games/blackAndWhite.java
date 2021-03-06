package Engine;
/*
 * basic.java
 *
 * Created on 22. oktober 2007, 09:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author STS-brbar
 */
public class blackAndWhite {
    
    public enum felter {
        BLACK, WHITE, BLANK
    }
    
    felter[][] pladen = new felter[10][10];
    
    felter tur;
    
    /** tilfjer de billeder som skal bruges */
    
    private static final ImageIcon WhiteButtonIcon = new ImageIcon("/Engine/White.JPEG");
    private static final ImageIcon BlackButtonIcon = new ImageIcon("/Engine/Black.JPEG");
    private static final ImageIcon BlankButtonIcon = new ImageIcon("/Engine/Blank.JPEG");
    
    /** laver en tester output */
    
    public void Output() {
        
        System.out.format("%n");
        
        for(int i = 0; i < 10; i++) {
            for(int y = 0; y < 10; y++) {
                System.out.format("(" + pladen[i][y] + ")");
            }
            System.out.format("%n");
        }
        
        System.out.format("%nDet er " + tur + " nu. %n");
    }
    
    /** Creates a new instance of basic */
    public blackAndWhite() {
        
        /* den frste tur er altid hvid */
        
        tur = felter.WHITE;
        
        /* alle felter er blanke */
        
        for(int i=0; i<10; i++) {
            for(int y=0; y<10; y++) {
                pladen[i][y] = felter.BLANK;
            }
        }
        
        /* midter felterne har nogle vrdier */
        
        pladen[4][4] = felter.WHITE;
        pladen[5][5] = felter.WHITE;
        pladen[4][5] = felter.BLACK;
        pladen[5][4] = felter.BLACK;        
    }
    
    /* man skal kunne skifte tur */
    
    private void skiftTur() {
        if(tur == felter.BLACK) {
            tur = felter.WHITE;
        }
        else {
            tur = felter.BLACK;
        }
    }
    
    /* man skal kunne skifte tilstanden p de forskellige felter */
    
    private void skiftFelt(int xKoordinat, int yKoordinat, felter skiftTil) {
        pladen[yKoordinat][xKoordinat] = skiftTil;
    }
    
    /* denne funktion skal bruges nr man s trykker p et felt */
    
    public void klikFelt(int xKoordinat, int yKoordinat) {
        
        if(SamletTest(xKoordinat, yKoordinat, tur)) {
            skiftFelt(xKoordinat, yKoordinat, tur);
            SAmletChange(xKoordinat, yKoordinat, tur);
            skiftTur();
        }
        else {
            System.out.format("%nDu mikke stte en brik der");
        }
    }
    
    /* jeg skal bruge 2 forskellige funktioner som ligner hinanden meget
     * den ene returner en boolean hvis den horisentalt, vertikalt eller diagonalt kan finde en lignende brik af samme farve
     * den anden ndre alle felterne til det felt er net.
     * begge funktioner skal deles op i 8 dele, en til hver retning p de forskelige "kalt'er" 
     */
    
    /* frst alle booleanerne */
    
    private boolean booleanHjre(int startX, int startY, felter tjekkerFarve) {
        
       if(startX + 2 < 10) {
        for(int i=2; i<10-startX; i++) {
            if(pladen[startY][startX+i]== tjekkerFarve) {
                return true;
            }
            else if(pladen[startY][startX+i] == felter.BLANK) {
                break;
            }
        }
        
       }
        
        return false;
    }
    
    private boolean booleanVenstre(int startX, int startY, felter tjekkerFarve) {
        
        
        if(startX-2 > -1) {
        for(int i=-2; i>-1; i--) {
            if(pladen[startY][startX+i]== tjekkerFarve) {
                return true;
            }
            else if(pladen[startY][startX+i] == felter.BLANK) {
                break;
            }
        }
        
        }
        
        return false;
    }
    
    private boolean booleanOp(int startX, int startY, felter tjekkerFarve) {
        
        if(startY-2 > -1) {
        for(int i=-2; i>-1; i--) {
            if(pladen[startY+i][startX]== tjekkerFarve) {
                return true;
            }
            else if(pladen[startY+i][startX] == felter.BLANK) {
                break;
            }
        }
        }
        
        return false;
    }
    
    private boolean booleanNed(int startX, int startY, felter tjekkerFarve) {
        
        if(startY+2 < 10) {
        for(int i=2; i<10-startX; i++) {
            if(pladen[startY+i][startX]== tjekkerFarve) {
                return true;
            }
            else if(pladen[startY+i][startX] == felter.BLANK) {
                break;
            }
        }
        
        }
        
        return false;
    }
    
    private boolean booleanNedHjre(int startX, int startY, felter tjekkerFarve) {
        
        int y = 2;
        
        if(startX+2 < 10 && startY+2 < 10) {
        for(int i=2; i<10; i++) {
                if(pladen[startY+y][startX+i] == tjekkerFarve) {
                    return true;
                }
                else if(pladen[startY+y][startX+i] == felter.BLANK) {
                break;
                }
                y++;
                
                /* denne linje srger for at uanset hvilken af de 2 retninger der rammer siderne frst, vil den breake */
                
                if(startY+y > 9) break;
        }
        
        }
        return false;
    }
    
    private boolean booleanNedVenstre(int startX, int startY, felter tjekkerFarve) {
        
        int y = 2;
        
        if(startX-2 > -1 && startY+2 < 10) {
        for(int i=-2; i>-1; i--) {
                
                if(pladen[startY+y][startX+i] == tjekkerFarve) {
                    return true;
                }
                else if(pladen[startY+y][startX+i] == felter.BLANK) {
                break;
                }
                y++;
                
                /* denne linje srger for at uanset hvilken af de 2 retninger der rammer siderne frst, vil den breake */
                
                if(startY+y > 9) break;
        }
        
        }
        return false;
    }
    
    private boolean booleanOpVenstre(int startX, int startY, felter tjekkerFarve) {
        
        int y = -2;
        
        if(startX-2 > -1 && startY-2 > -1) {
        for(int i=-2; i>-1; i--) {
                
                if(pladen[startY+y][startX+i] == tjekkerFarve) {
                    return true;
                }
                else if(pladen[startY+y][startX+i] == felter.BLANK) {
                break;
                }
                y--;
                
                /* denne linje srger for at uanset hvilken af de 2 retninger der rammer siderne frst, vil den breake */
                
                if(startY+y < 0) break;
        }
        
        }
        return false;
    }
    
    private boolean booleanOpHjre(int startX, int startY, felter tjekkerFarve) {
        
        int y = -2;
        
        if(startY-2 > -1 && startX+2 < 10) {
        for(int i=2; i<10; i++) {
                
                if(pladen[startY+y][startX+i] == tjekkerFarve) {
                    return true;
                }
                else if(pladen[startY+y][startX+i] == felter.BLANK) {
                break;
                }
                y--;
                
                /* denne linje srger for at uanset hvilken af de 2 retninger der rammer siderne frst, vil den breake */
                
                if(startY+y < 0) break;
        }
        }
        
        return false;
    }
    
    /* s kommer alle de funktioner der retunere selve koordinaterne */
    
    private void skiftHjre(int startX, int startY, felter tjekkerFarve) {
         
        for(int i=1; i<10-startX; i++) {
            if(pladen[startY][startX+i]!= tjekkerFarve) {
                skiftFelt(startX+i, startY, tjekkerFarve);
            }
            else break;
        }
    }
    
    private void skiftVenstre(int startX, int startY, felter tjekkerFarve) {
       
        for(int i=-1; i>-1; i--) {
            if(pladen[startY][startX+i]!= tjekkerFarve) {
                skiftFelt(startX+i, startY, tjekkerFarve);
            }
            else break;
        }
    }
    
    private void skiftOp(int startX, int startY, felter tjekkerFarve) {
       
        for(int i=-1; i>-1; i--) {
            if(pladen[startY+i][startX]!= tjekkerFarve) {
                skiftFelt(startX, startY+i, tjekkerFarve);
            }
            else break;
        }
    }
    
    private void skiftNed(int startX, int startY, felter tjekkerFarve) {
       
        for(int i=1; i<10-startX; i++) {
            if(pladen[startY+i][startX]!= tjekkerFarve) {
                skiftFelt(startX, startY+i, tjekkerFarve);
            }
            else break;
        }
    }
    
    private void skiftNedHjre(int startX, int startY, felter tjekkerFarve) {
      
        int y = 1;
        
        for(int i=1; i<10; i++) {
                if(pladen[startY+y][startX+i] != tjekkerFarve) {
                    skiftFelt(startX+i, startY+y, tjekkerFarve);
                }
                
                else {
                    break;
                }
                y++;
                
                /* denne linje srger for at uanset hvilken af de 2 retninger der rammer siderne frst, vil den breake */
                
                if(startY+y > 9) break;
        }
    }
    
    private void skiftNedVenstre(int startX, int startY, felter tjekkerFarve) {
        
        int y = 1;
        
        for(int i=-1; i>-1; i--) {
                if(pladen[startY+y][startX+i] != tjekkerFarve) {
                    skiftFelt(startX+i, startY+y, tjekkerFarve);
                }
                
                else {
                    break;
                }
                y++;
               
                /* denne linje srger for at uanset hvilken af de 2 retninger der rammer siderne frst, vil den breake */
                
                if(startY+y > 9) break;
        }
    }
    
    private void skiftOpVenstre(int startX, int startY, felter tjekkerFarve) {        
        
        int y = -1;
        
        for(int i=-1; i>-1; i--) {
                if(pladen[startY+y][startX+i] != tjekkerFarve) {
                    skiftFelt(startX+i, startY+y, tjekkerFarve);
                }
                
                else {
                    break;
                }
                y--;
                
                /* denne linje srger for at uanset hvilken af de 2 retninger der rammer siderne frst, vil den breake */
                
                if(startY+y < 0) break;
        }
    }
    
    private void skiftOpHjre(int startX, int startY, felter tjekkerFarve) {
        
        int y = -1;
        
        for(int i=1; i<10; i++) {
                
                if(pladen[startY+y][startX+i] != tjekkerFarve) {
                    skiftFelt(startX+i, startY+y, tjekkerFarve);
                }
                
                else {
                    break;
                }
                y--;
                
                /* denne linje srger for at uanset hvilken af de 2 retninger der rammer siderne frst, vil den breake */
                
                if(startY+y < 0) break;
        }
    }
    
    /* s 2 metoder til at frst, bestemme om det er lovligt at lgge en brik, og derefter en metode til at ndre alle brikker */
    
    private boolean SamletTest(int startX, int startY, felter tjekkerFarve) {
        
        return (booleanHjre(startX,startY,tjekkerFarve) || 
                booleanVenstre(startX,startY,tjekkerFarve) || 
                booleanOp(startX,startY,tjekkerFarve) || 
                booleanNed(startX,startY,tjekkerFarve) ||
                booleanNedHjre(startX,startY,tjekkerFarve) ||
                booleanNedVenstre(startX,startY,tjekkerFarve) ||
                booleanOpVenstre(startX,startY,tjekkerFarve) ||
                booleanOpHjre(startX,startY,tjekkerFarve));
        
    }
    
    private void SAmletChange(int startX, int startY, felter tjekkerFarve) {
        
        if(booleanHjre(startX,startY,tjekkerFarve)) {
            skiftHjre(startX,startY,tjekkerFarve);
        }
        if(booleanVenstre(startX,startY,tjekkerFarve)) {
            skiftVenstre(startX,startY,tjekkerFarve);
        }
        if(booleanOp(startX,startY,tjekkerFarve)) {
            skiftOp(startX,startY,tjekkerFarve);
        }
        if(booleanNed(startX,startY,tjekkerFarve)) {
            skiftNed(startX,startY,tjekkerFarve);
        }
        if(booleanNedHjre(startX,startY,tjekkerFarve)) {
            skiftNedHjre(startX,startY,tjekkerFarve);
        }
        if(booleanNedVenstre(startX,startY,tjekkerFarve)) {
            booleanNedVenstre(startX,startY,tjekkerFarve);
        }
        if(booleanOpVenstre(startX,startY,tjekkerFarve)) {
            booleanOpVenstre(startX,startY,tjekkerFarve);
        }
        if(booleanOpHjre(startX,startY,tjekkerFarve)) {
            booleanOpHjre(startX,startY,tjekkerFarve);
        }
        
        
    }
    
    public Color baggrund() {
        Color farve;
        
        if (tur == felter.WHITE) {
            farve = Color.white;
        }
        else {
            farve = Color.black;
        }
        
        return farve;
    }
    
    private boolean feltFarveTjekker(int startX, int startY, felter tjekkerFarve) {
        if(pladen[startY][startX] == tjekkerFarve) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public ImageIcon KnapIcon(int x, int y) {
        
        if(feltFarveTjekker(x,y,felter.WHITE)) {
                   return WhiteButtonIcon;
                }
        else if(feltFarveTjekker(x,y,felter.BLACK)) {
                   return BlackButtonIcon;
                }
                
        else {
                   return BlankButtonIcon; 
                }
    }

}

    

