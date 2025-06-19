import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.1 -- 08-07-2017
 */
public class MyDodo extends Dodo
{

    public MyDodo() {
        this ( EAST );
    }

    public MyDodo( int init_direction ) {
        super ( init_direction );
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, 
     * i.e. there are no obstructions or end of world in the cell in front of her.
     * 
     * <p> Initial:   Dodo is somewhere in the world
     * <p> Final:     Same as initial situation
     * 
     * @return  boolean true if Dodo can move (thus, no obstructions ahead)
     *                  false if Dodo can't move
     *                      i.e. there is an obstruction or end of world ahead
     */
    public boolean canMove() {
        if ( borderAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
        }
    }

    /**
     * Dodo moves forward and sits on the egg.
     * 
     * <p> Initial:   Somewhere in a cell ahead of Dodo lies a egg. 
     *                The cells between Dodo and the egg are empty.
     * <p> Final:     Dodo has moved forward and is sitting on the egg.
     *                      Dodo is facing original direction.
     * 
     */
    public void gotoEgg() {
        while(onEgg()!=true){
        move();
    }
        turn2x90();
    }

    
    
     /**
     * Test there is a grain in the cell in front of Dodo 
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return  boolean true if there is a grain in the cell in front of Dodo
     *                  false else otherwise
     */
    public boolean grainAhead() {
        move();
        if ( onGrain() ){
            // Dodo goes back to initial situation before returning value
            // E
            stepBack();
            return true;
        } else {
            // Dodo goes back to initial situation before returning value
            // F
            stepBack();
            return false;
        }
    } 
        public void turn2x90() {
        if ( canMove() ) {
            turnRight();
            turnRight();
        } else {
            showError( "I'm stuck!" );
        }
    }
    public void climbOverFence() {
        turnLeft();
         if ( canMove() ) {
            move();
        } else {
            showError( "I'm stuck!" );
        }
        turnRight();
         if ( canMove() ) {
            move();
            move();
        } else {
            showError( "I'm stuck!" );
        }
        turnRight();
                 if ( canMove() ) {
            move();
        } else {
            showError( "I'm stuck!" );
        }
        turnLeft();
         
        
    }
    public void grabGrain() {
        if(grainAhead() ==true) {
            move();
            stepBack();
        }  else {
             showError( "no grains!" );
        }
    }
    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */
    public boolean canLayEgg( ){
        if( onEgg() ) {
            return false;
        }else{
            return true;
        }
    }  
    public void walkToWorldEdge( ){
        while( canMove() && borderAhead() == false ){
            move();
        }
    }
    public void goBackToStartOfRowAndFaceBack(){
        setDirection(WEST);
        walkToWorldEdge();
        setDirection(EAST);
        System.out.println(" end of goBackToStartOfRowAndFaceBack");
    }
    public void walkToWorldEdgeClimbingOverFences( ){
        while( canMove() ){
            if(fenceAhead()==true){
                climbOverFence();
            }
            if(onNest() && (onEgg()==false)){
                layEgg();
            }
            if(!fenceAhead()){
                move();
            }
        }
    }
    public void pickUpGrainsAndPrintCoordinates(){
        while( canMove() ){
            int count = 0;
            if(onGrain()){
            int Y = getY();
            int X = getX();
            System.out.println("coordinate y = "+Y+"and coordinate x = " +X);
            count=1;
            }
            move();
            if(onGrain()&&(count==0)){
            int i = getY();
            int k = getX();
            System.out.println("coordinate y = "+i+"and coordinate x = " +k);
            }
         }
    }
    public void stepBack(){
         turn2x90();
         move();
         turn2x90();
        
    }
    public void walkToBorderLayingEggs(){
        while (canMove() ){
            move();
            if (onNest() &&(onEgg()==false)){
            layEgg();
            }
        }
    }
    public void walkAroundFencedArea(){
        while(canMove() && onEgg()==false && onNest()==false){
            turnRight();
            if(fenceAhead()==false){
            move();
            }
            else if(fenceAhead()){
            turnLeft();
                if(fenceAhead()==false){
                move();
                }
                else{
                    turnLeft();
                }
            }
            else{
                turnRight();
                move();
            }
        }  
    }
    public void followEggTrail(){
        while(onNest()==false)
            if(eggAhead()){
                removeTouching(Egg.class);
                move();
            }
            else if (nestAhead()){
                removeTouching(Egg.class);
                move();
            }
            else{
                turnRight();
            }
    }
    public void faceEast(){
        while(getDirection() != EAST)
        turnRight();
        System.out.println("dodo is looking East");
    }
    public void goToLocation(int coordX, int coordY){
        if (validCoordinates(coordX, coordY)==true){
        if(getX() < coordX){
           setDirection(EAST);
           while (getX() != coordX)
           move();
        }
        else if(getX() > coordX){
            setDirection(WEST);
            while (getX() != coordX){
            move();
            }
        }
        
        if(getY() < coordY){
           setDirection(SOUTH);
           while (getY() != coordY)
           move();
        }
        else if(getY() > coordY){
            setDirection(NORTH);
            while (getY() != coordY){
            move();
            }
        }
        }
        setDirection(EAST);
    }
    public boolean validCoordinates(int x, int y){
        int i = getWorld().getWidth();
        int j = getWorld().getHeight();
        if(x < i && y < j){
        return true;
        }
        else{
            System.out.println("invalid coordinates");
            return false;
        }
    }
        public int countEggsInRow( ){
        int eggs = 0;
        int i = getWorld().getWidth()-1;
        int j = getWorld().getHeight()-1;
        boolean turn = false;
        boolean finished = false;
        while(finished == false){
            if(getX() < i && turn == false){
                setDirection(EAST);
                while (getX() != i){
                    
                   move();
                }
                setDirection(WEST);
                turn = true;
                while (getX() != 0){
                    if(onEgg()){
                        eggs++;
                    }
                    move();
                }
                if (getX() == 0){
                    setDirection(EAST);
                    finished = true;
                }
            }
        }
        return eggs;
    }
        public void layTrailOfEggs(int input){
        int check = input;
        if (getWorld().getWidth()-1 > input)
            while (canMove() &&  check > 0){
            check--;
            move();
            if(onEgg()==false){
            layEgg();
            }
        }
    }
    public int eggInWorld(){
        boolean fin = false;
        int width = getWorld().getWidth()-1;
        int l = 0;
        int eierenlijst = 0;
        int rijnummer = 0;
        while(fin == false){
            int o = countEggsInRow();
            int rijplaatsing = getY();
            int k =+ o;
            if (eierenlijst < o){
                eierenlijst = o;
                System.out.println(rijplaatsing+"heeft de meeste eieren");
                rijnummer = rijplaatsing;
            }
            setDirection(SOUTH);
            if(canMove()==true){
                move();
                setDirection(EAST);
                move();
            }
            if(getX() == 0 && getY() == width){
                fin = true; 
            }
        }
        while(fin==true){
            setDirection(NORTH);
              if(canMove()==true){
                move();
            }
            if(canMove()==false){
                setDirection(EAST);
                break;
            }
        }
        return rijnummer;
    }   
    public void monumentOfEggs(){
        int rowcount = 0;
        int stepsInRow = 0;
        int width = getWorld().getWidth()-1;
        int height = getWorld().getHeight()-1;
        boolean turn = false;
        boolean finished = false;
        boolean rowDone = false;
        System.out.println(rowcount+" "+stepsInRow);
        while(finished == false){
            while(rowDone == false){
                stepsInRow = rowcount;
                    while (getX() != width){
                    if(stepsInRow != 0){
                    System.out.println(rowcount+" "+stepsInRow);
                    layEgg();
                    move();
                    stepsInRow--;
                    }
                    else{
                    move();
                    }
                    }
                    setDirection(WEST);
                    while (getX() != 0){
                        move();
                    }
                    if (getX() == 0){
                        rowDone = true;
                    }
            }
            setDirection(SOUTH);
            while(rowDone == true && rowcount < getWorld().getHeight() ){
                if(canMove()==true){
                 move();
                 setDirection(EAST);
                 stepsInRow =0;
                 rowDone = false;
                 rowcount++;
                }
                if(canMove() == false){
                    break;
                }
            }
             if(getY() == height && getX() == 0){
                finished = true;
                while(canMove()){
                    if(onEgg()==true){
                        move();
                        layEgg();  
                    }
                    else{
                        layEgg();
                        move();
                        layEgg();
                    }
                    if((getY() == height && getX() == width)){
                        break;
                    }
                }
                }
        }
    }
    public void sturdyMonument(){
        int rowcount=0;
        int eggsInLastRow = 0;
        int stepsInRow = rowcount;
        int width = getWorld().getWidth()-1;
        int height = getWorld().getHeight()-1;
        boolean turn = false;
        boolean finished = false;
        boolean rowDone = false;
        while(finished == false){
            while(rowDone == false){
                stepsInRow = rowcount;
                    while (getX() != width){
                    if(stepsInRow != 0){
                    System.out.println("rowcount is "+rowcount+" eggstogo "+stepsInRow);
                    layEgg();
                    move();
                    stepsInRow--;
                    }
                    else{
                    move();
                    }
                    }
                    setDirection(WEST);
                    while (getX() != 0){
                        move();
                    }
                    if (getX() == 0){
                        rowDone = true;
                    }
            }
            setDirection(SOUTH);
            while(rowDone == true && rowcount < getWorld().getHeight() ){
                if(canMove()==true){
                 move();
                 setDirection(EAST);
                 stepsInRow =0;
                 rowDone = false;
                 rowcount++;
                }
                if(canMove() == false){
                    break;
                }
            }
             if(getY() == height && getX() == 0){
                finished = true;
                while(canMove()){
                    if(onEgg()==true){
                        move();
                        layEgg();  
                    }
                    else{
                        layEgg();
                        move();
                        layEgg();
                    }
                    if((getY() == height && getX() == width)){
                        break;
                    }
                }
                }
        }
    }
    public void piramide(){
        int width = 1;
        while (true) {
            setDirection(WEST);
            for (int i = 0; i < width / 2; i++) {
                if (canMove()){
                    move();   
                }
            }
            setDirection(EAST);
            for (int j = 0; j < width; j++) {
                layEgg();
                if (j < width - 1 && canMove()){
                    move();
                }
            }
            setDirection(WEST);
            for (int i = 0; i < width / 2; i++) {
                if (canMove()){
                    move();
                }
            }
            setDirection(SOUTH);
            if (canMove()) {
                move();
                width += 2;
            } else {
                break;
            }
        }
    }
    public double averageEggsInRow( ){
        double eggsTotal = 0;
        int i = getWorld().getWidth()-1;
        int j = getWorld().getHeight()-1;
        boolean turn = false;
        boolean finished = false;
        double eggs =0.0;
        while(finished == false){
            if(getX() < i && turn == false){
                setDirection(EAST);
                while (getX() != i){
                    
                   move();
                }
                setDirection(WEST);
                turn = true;
                while (getX() != 0){
                    if(onEgg()){
                        eggsTotal++;
                    }
                    move();
                }
                if (getX() == 0){
                    setDirection(EAST);
                    finished = true;
                }
            }
        }
        eggs = eggsTotal / getWorld().getWidth();
        System.out.println(eggs+""+eggsTotal+" " +getWorld().getWidth());
        return eggs;
    }
    public void PariteitsbitAlgoritme() {
    controleerHorizontalePariteit();
    controleerVerticalePariteit();
    goToLocation(0, 0);
    setDirection(EAST);
    }
    public void controleerHorizontalePariteit() {
        goToLocation(0, 0);
        setDirection(EAST);
    
        while (true) {
            int eieren = 0;
    
            // Tel eieren in de rij
            if (onEgg()) eieren++;
            while (getX() < getWorld().getWidth() - 1) {
                move();
                if (onEgg()) eieren++;
            }
    
            // Als oneven aantal eieren ? probeer gouden ei te leggen
        if (eieren % 2 != 0) {
            setDirection(WEST);; // draai 180 graden, nu WEST
            // Loop terug in de rij tot je een vrije plek vindt of begin bereikt
            while (getX() > 0 && !canLayEgg()) {
                move();
            }
            // Check of we op een plek staan waar we kunnen leggen
            if (canLayEgg()) {
                layGoldenEgg();
            } else {
                System.out.println("?? Geen plek voor gouden ei gevonden in deze rij.");
            }
            // Zorg dat we altijd aan het begin van de rij eindigen
            setDirection(WEST);
            while (getX() > 0) {
                move();
            }
            setDirection(EAST);  // klaar voor volgende rij
        } else {
            // Als het aantal eieren even is, ga gewoon terug naar het begin van de rij
            setDirection(WEST);
            while (getX() > 0) {
                move();
            }
            setDirection(EAST);
        }
    
            // Als laatste rij is bereikt, stop
            if (getY() == getWorld().getHeight() - 1) break;
    
            // Ga naar volgende rij
            setDirection(SOUTH);
            move();
            setDirection(EAST);
        }
    }
    public void controleerVerticalePariteit() {
        goToLocation(0, 0);
        setDirection(SOUTH);
    
        while (true) {
            int eieren = 0;
    
            // Tel eieren in de kolom
            if (onEgg()) eieren++;
            while (getY() < getWorld().getHeight() - 1) {
                move();
                if (onEgg()) eieren++;
            }
    
            // Als oneven: probeer ei te leggen op een vrije plek in dezelfde kolom
            if (eieren % 2 != 0) {
                // Ga terug naar boven van de kolom
                setDirection(NORTH);
                while (getY() > 0) {
                    move();
                }
    
                // Loop naar beneden om een lege plek te vinden
                setDirection(SOUTH);
                while (!canLayEgg() && getY() < getWorld().getHeight() - 1) {
                    move();
                }
    
                if (canLayEgg()) {
                    layEgg();
                } else {
                    System.out.println("?? Geen plek voor ei gevonden in deze kolom.");
                }
            } else {
                // Als even, ga gewoon terug naar boven van de kolom
                setDirection(NORTH);
                while (getY() > 0) {
                    move();
                }
            }
    
            // Volgende kolom?
            if (getX() == getWorld().getWidth() - 1) break;
    
            // Beweeg naar volgende kolom
            setDirection(EAST);
            move();
            setDirection(SOUTH);
        }
    }
    public void noCompassParity(){
        int eggs = 0;
    }
}
