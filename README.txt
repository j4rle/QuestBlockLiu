Vid bedömning av projekt gör assistenten först en grundlig genomgång.
Examinatorn använder sedan kommentarerna som underlag för en egen
genomgång och en slutlig bedömning.

Bedömningen fokuserar på förbättringsmöjligheter, eftersom det är
detta som kan leda till komplettering och till ny förståelse för
alternativa sätt att programmera.  Tyvärr har vi ofta inte tid att
uttryckligen påpeka allt det positiva vi ser i projekten!

Eventuella kompletteringar lämnas till *assistenten* inför detta års
sista kompletteringsomgång i början av januari.  Därefter hänvisar vi
till inlämning under TDDD78-2015.

Examinatorns kommentarer:
--------------------------------------------------

Som assistenten skriver är detta en stor förbättring.  Ni har några
saker kvar att fixa om ni vill få betyg 5, men det räcker bra för
betyg 4 nu.  Jag sätter det betyget nu så att ni får era poäng, så får
ni välja om ni vill plussa till en femma i nästa inlämningsomgång.

Övriga kommentarer (av assistent)
--------------------------------------------------

This is a big improvement over the last version both with code and documentation.
There are still issues to be fixed though, especially when you aim at getting a 5. See below for more detailed comments.

public static final int MAINMENUSTATE = 0; etc. should be an enum
	-replaced old constant integers with enums. The different game states are organized in a map structure with enums as keys.

The randomize method in TileMap is always creating a new Random object - it will generate a poor quality random numbers. Instead, create a Random object once in TileMap class constructor.
	-fixed

START,HELP, QUIT constants should not be local.

When conditions are mutually exclusive, use if/else, or in some cases if / else if:

if(sprinting){ //player is sprinting
 maxSpeed = 10;
 moveSpeed = 0.6;
}
if(!sprinting){ //player not sprinting
 maxSpeed = 5.1;
 moveSpeed = 0.5;
}

instead:

if(sprinting){
...
} else {
...
}

The same for sliding, dx, dy etc.  This is both more readable (you can
see the structure better) and more efficient.

The update() method in Player class seem overly complex and there is some code repetition. Example improvement in handling the dx updates based on left/right conditions:
if (left) dx = updateSpeed(dx,-moveSpeed,-maxSpeed);
else if (right) dx = updateSpeed(dx,moveSpeed,maxSpeed);
else if (dx<0) dx = updateSpeed(dx,stopSpeed,0);
else dx = updateSpeed(dx,-stopSpeed,0);
with the additional helper updateSpeed method:
public double updateSpeed(double dx,double moveSpeed, double maxSpeed){
  dx += moveSpeed;
  if (Math.abs(dx) > maxSpeed)
    dx = maxSpeed;
  return dx;
}

Many inspection warnings do not have a comment and many should still be fixed.


==============================

Older comments:

There is a lot of overlap between Level1State and Level2State. Arguably these classes should not exist when you are able to load the maps from a file.

	- Classes replaced with one LoadLevel class



GameState is an abstract class that only contains abstract methods. Perhaps this should have been an interface.

	- Changed class GameState to interface.



The GamePanel contains the run method, which should not be the task of a UI. In this run method, you make use of a busy wait with your while-loop, which can be a big problem! It will consume a lot of CPU power doing nothing. There should be some sleeping mechanism, or better, a Timer that takes a method as a callback at a set interval like done in the Tetris lab.

	- Placed run method and other functions in the class GameEngine. Changed from old method with Thread to Timer-class.



In the Movable class you maintain a number of protected booleans for the various movements that can occur. However, there does not seem to be a need to store these as class variables this way.

	- Made the booleans local in player class



In the report you have kept the skeleton text that was supposed to be replaced, highlighted in yellow alongside your index. The same goes for the code that does not stick to proper indentation, probably due to copying it over from another IDE. 

	- Removed skeleton text



The UML diagram does not use proper UML. The arrows are generalisation arrows used when extending classes. You should  relate diagrams to your text by highlighting aspects of your design.

	- 4 new UML diagrams added, showing different aspects of the project. 



Missing UML diagrams - only one included (4 needed for grade 5).

	-Added 4 UML diagrams.



Finally, the user manual does not explain your game, nor does it explain how it works, which is the purpose of a manual.

	- Manual updated, added a more in-depth explanation of the game and menu system.



While you use class extension, this by itself does not qualify as a Template design pattern. There is no general functionality for the Movable class with a specific behavioral element that is implemented by the concrete classes.

	- Removed Template as a design-pattern. Replaced with the Model-View-Controller pattern.



You define WIDTH and HEIGHT yet for some constants still go with magic numbers

Why not define a constant for nsPerTick e.g. TICKS_UPDATE_RATE_60FPS = 1000000000.0D / 60.0D?

The same goes for other magic numbers like font size, max&moveSpeed etc.

Those numbers are pretty much constants in your project.

	- Removed most of the magic numbers and renamed variables for clarity



No comments to "Constructor assigns value to field defined in superclass" warning...

A proper way to solve this issue is to create a constructor for LevelState and assign gsc there:

public LevelState(GameStateControl gameStateControl)

Then in the classes that extend LevelState call super(gsc) instead of this.gsc = gsc;

In case of Movable, since there is quite many variables you want to set you could create setter methods.

	-Fixed the issued with superclasses. Also added some relevant setters in movable.



Naming:

- avoid using short variable names e.g. gsc, bg

- constants should be all capitals: tileScale, playerSizeOffset, startX, startY, e.g. tileScale -> TILE_SCALE

- helpVisible instead of help boolean variable name, reflects the meaning better,

- playerX, playerY, maybe use playerXPos, playerXPos as it is the position (I assume), the same for setPlayer method,

	-Fixed most of the shorter variable names.

	-Constants rewritten in all-caps.



To solve the "Resource management issues" use try-with-resources as suggested by IDEA:

http://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html

	-Fixed all resource management issues by using try-with or try followed by finally.



"Numeric issues":

tempY = (currentRow + 1) * tileMap.getTileSize() - height / 2; should be

tempY = (currentRow + 1) * tileMap.getTileSize() - height / 2.0;

	-Fixed.



TileMap, draw method:

It would be much better to declare constants or use enum for tile types instead of 0,1 and 2.

	-Declared enums for different tiles and replaced multiple if-statements with switch-case.



Similar to the above comment: MainMenu, select method, currentChoice == 0, 1 and 2.

	-Fixed by using constants.



BIGGEST CHANGES IN PROJECT SINCE LAST DEMONSTRATION:

* Tile is now a class. From the input file the transformation of info looks like this: int -> tile enum -> Tile object based on enum -> placed in an array of Tiles.

* Changes in the way we handled tiles enabled us to add several new tiles. Among these are water, falling tiles and the "goal line", finishtile.

* High scores added. High scores for all levels will be written to a text-file in the res-folder.

* Randomized-level is very broken, only kept to show a concept that could be used in further development of levels(not much time was used to design levels).
