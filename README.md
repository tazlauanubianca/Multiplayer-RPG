# Multiplayer RPG

We are in an MMO-style game. Our heroes spend their lives in a 2D universe, which they explore and develop.

As it is known from MMO and RPG games, our characters are diverse. Each one belongs to a certain class and has a certain set of skills that are best understood and can be developed most quickly; we will analyze them in more detail below.

At the beginning of the game, our heroes are placed on the map in well-defined places. Then there will be unit duration rounds, in which all the heroes will execute a certain movement (well defined) on the map.

When two heroes arrive in the same place, they will fight. In this round, each combat hero will use all available abilities against the opponent, once. After the fight, they will see their way from the next round.

At the end of the game (a set number of rounds, with initial locations and set moves), our program will look at the heroes left alive.

## Map
The map on which the game is played is a 2D rectangle, composed of locations ("squares") of unit size. Each location has a certain set of properties; the types of locations are:
* Land
* Volcanic
* Desert
* Woods

## Heroes
The types of heroes available in LOOP are:
* Knight
* Pyromancer
* Rogue
* Wizard

They all have a number of hit points (HP, also known as "life") and a number of experience points (XP). There is also a level-up mechanism depending on the experience gained.
